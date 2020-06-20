package com.zk.activiti6.chapter;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSetMultimap;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class FirstActTest {

    @Test
    public void test() {
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("chapter/activiti.cfg.xml");
        ProcessEngine engine = engineConfiguration.buildProcessEngine();
        // 资源部署，
        RepositoryService rs = engine.getRepositoryService();
        rs.createDeployment().addClasspathResource("chapter/bpmn/first.bpmn").deploy();
        // 运行时服务
        RuntimeService runService = engine.getRuntimeService();
        // 任务服务
        TaskService taskService = engine.getTaskService();

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

    }

    @Test
    public void test2() {
        ImmutableMap<String, Integer> map = ImmutableMap.<String, Integer>builder().put("a", 1).build();
        System.out.println("ImmutableMap: "+map);
        ImmutableSetMultimap<String, Integer> multimap = map.asMultimap();
        System.out.println("multimap: " + multimap);
        multimap.put("a", 3);
        System.out.println("multimap: " + multimap);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
