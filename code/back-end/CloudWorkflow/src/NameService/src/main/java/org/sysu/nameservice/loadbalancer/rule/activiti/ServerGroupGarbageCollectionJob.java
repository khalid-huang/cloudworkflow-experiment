package org.sysu.nameservice.loadbalancer.rule.activiti;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.sysu.nameservice.GlobalContext;
import org.sysu.nameservice.loadbalancer.ActivitiLoadBalancerStats;
import org.sysu.nameservice.loadbalancer.BaseLoadBalancer;
import org.sysu.nameservice.loadbalancer.ILoadBalancer;
import org.sysu.nameservice.loadbalancer.ServerGroup;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: Gordan Lin
 * @create: 2018/11/30
 **/
public class ServerGroupGarbageCollectionJob implements Job {

    public ServerGroupGarbageCollectionJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<String, ILoadBalancer> serviceIdToLoadBalancer = (Map<String, ILoadBalancer>) context.getJobDetail().getJobDataMap().get("serviceIdToLoadBalancer");
        Set<String> serviceIds = serviceIdToLoadBalancer.keySet();
        long now = new Date().getTime();
        for (String serviceId : serviceIds) {
            BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) serviceIdToLoadBalancer.get(serviceId);
            ActivitiLoadBalancerStats loadBalancerStats = (ActivitiLoadBalancerStats) baseLoadBalancer.getLoadBalancerStats();
            Map<String, ServerGroup> proInstanceIdToServerGroup =  loadBalancerStats.getProInstanceIdToServerGroup();

            Iterator<Map.Entry<String, ServerGroup>> it = proInstanceIdToServerGroup.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, ServerGroup> entry = it.next();
                if (now - entry.getValue().getLastUse() > GlobalContext.GARBAGE_COLLECTION_TIMESTAMP) {
                    it.remove();
                }
            }
            System.out.println("服务器组数量：" + proInstanceIdToServerGroup.size());
        }
    }

    public void schedulerJob(Map<String, ILoadBalancer> serviceIdToLoadBalancer) throws SchedulerException, InterruptedException {
        // 创建调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(ServerGroupGarbageCollectionJob.class).withIdentity("job1", "group1").build();
        jobDetail.getJobDataMap().put("serviceIdToLoadBalancer", serviceIdToLoadBalancer);

        // 创建触发器 每小时执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(10000).repeatForever()).build();

        // 将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);

        // 调度器开始调度任务
        scheduler.start();
    }
}
