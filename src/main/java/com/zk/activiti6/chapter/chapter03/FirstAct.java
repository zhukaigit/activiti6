package com.zk.activiti6.chapter.chapter03;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class FirstAct {

    public static void main(String[] args) throws Exception {
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("chapter/chapter03/activiti.cfg.xml");
        ProcessEngine engine = engineConfiguration.buildProcessEngine();
        // 存储服务
        RepositoryService rs = engine.getRepositoryService();
        // 运行时服务
        RuntimeService runService = engine.getRuntimeService();
        // 任务服务
        TaskService taskService = engine.getTaskService();

        rs.createDeployment().addClasspathResource("chapter/chapter03/first.bpmn").deploy();

        ProcessInstance pi = runService.startProcessInstanceByKey("myProcess");

        // 普通员工完成请假的任务
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("当前流程节点：" + task.getName());
        taskService.complete(task.getId());

        // 经理审核任务
        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("当前流程节点：" + task.getName());
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("流程结束后：" + task);

        engine.close();
        System.exit(0);
    }

}
