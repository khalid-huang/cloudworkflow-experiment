package org.sysu.processexecutionservice.scheduler.rule;

import com.netflix.loadbalancer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


public class ActivitiRule extends RoundRobinRule {

    private static Logger logger = LoggerFactory.getLogger(ActivitiRule.class);

    private final Map<String, Set<Server>> proDefinitionIdToServerGroup =
            Collections.synchronizedMap(new LinkedHashMap<String, Set<Server>>(50, 0.75f, true));

    public ActivitiRule() {
        super();
    }

    public ActivitiRule(ILoadBalancer lb) {
        super(lb);
    }

    public Server chooseServer(ILoadBalancer lb, Object key) {
        if(lb == null) {
            logger.warn("no load balancer");
            return null;
        }
        Server server = null;

        int count = 0; //尝试10次；
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            int upCount = reachableServers.size();
            if((upCount == 0)) {
                logger.warn("no up servers available from load balancer");
                return null;
            }

            AbstractLoadBalancer nlb = (AbstractLoadBalancer) lb;
            LoadBalancerStats stats = nlb.getLoadBalancerStats();
            server = _choose(reachableServers, stats, key);
            if(server == null) {
                Thread.yield();
                continue;
            }
            if(server.isAlive() && server.isReadyToServe()) {
                return server;
            }
            server = null;
        }
        if(count >= 10) {
            logger.warn("No available alive servers after 10 tries from load balancer: " + lb);
        }
        return server;
    }

    private Server _choose(List<Server> reachableServer, LoadBalancerStats stats, Object key) {
        if (stats == null) {
            logger.warn("no statistics, nothing to do so");
            return null;
        }

        String processDefinitionId = (String) key;
        Set<Server> servers = proDefinitionIdToServerGroup.get(processDefinitionId);
        Server result = null;
        // 第一次执行流程定义
        if (servers == null || servers.size() == 0) {
            logger.info("第一次执行该流程定义----------");
            result = super.choose(key);
            servers = new HashSet<>();
            servers.add(result);
            proDefinitionIdToServerGroup.put(processDefinitionId, servers);
        }
        else {
            logger.info("从之前执行过的引擎中选择----------");
            List<Server> previousServerList = new ArrayList<>(servers);

            result = previousServerList.get(0);
        }
        return result;
    }

    public Server choose(Object key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String uri = request.getRequestURI();

        // 查询直接处理
        if (uri.contains("get")) return super.choose(key);

        //获取processDefinitionId的值
        int startIndex = uri.indexOf('/', 1)+1;
        int endIndex = uri.indexOf('/', startIndex);
        if (endIndex == -1) endIndex = uri.length();
        String processDefinitionId = uri.substring(startIndex, endIndex);
        return chooseServer(getLoadBalancer(), processDefinitionId);
    }

}
