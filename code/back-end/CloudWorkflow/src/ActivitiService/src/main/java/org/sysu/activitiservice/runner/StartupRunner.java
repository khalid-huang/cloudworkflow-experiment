package org.sysu.activitiservice.runner;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

@Order(1)
public class StartupRunner implements CommandLineRunner {
    @Autowired
    RuntimeService runtimeService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("初始化启动一个流程实例");
        ProcessInstance pi =  runtimeService.startProcessInstanceByKey("online-shopping");
        System.out.println("启动完成：" + pi.getId());
    }
}
