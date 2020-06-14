package com.zk.activiti6.chapter;

import com.zk.activiti6.utils.ActivitiUtil;
import com.zk.activiti6.utils.FileUtil;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhukai on 2020/6/14
 */
public class SecondActImageTest {

    @Test
    public void test1() throws Exception {
        // 获取流程引擎
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("chapter/activiti.cfg.xml");
        ProcessEngine engine = engineConfiguration.buildProcessEngine();
        String processInstanceId = null;
        try {
            engine = ProcessEngines.getDefaultProcessEngine();
            // 存储服务
            RepositoryService rs = engine.getRepositoryService();
            // 运行时服务
            RuntimeService runService = engine.getRuntimeService();
            // 任务服务
            TaskService taskService = engine.getTaskService();
            List<Deployment> list = rs.createDeploymentQuery().deploymentName("secondBpmnName")
                    .deploymentKey("secondBpmnKey").deploymentCategory("secondBpmnCategory").list();
            if (list == null || list.size() == 0) {
                Deployment deploy = rs.createDeployment().addClasspathResource("chapter/bpmn/second.bpmn")
                        .name("secondBpmnName").key("secondBpmnKey").category("secondBpmnCategory").deploy();
                System.out.println("开始部署流程: " + deploy.getId() + " - " + deploy.getName() + " - " + deploy.getKey());
            } else {
                System.out.println("已有对应的流程资源,不再重新部署");
            }

            String bizKeyRandom = UUID.randomUUID().toString();
            ProcessInstance pi = runService.startProcessInstanceByKey("vocationPro",
                    "vacationProBizKey_" + bizKeyRandom);
            System.out.println("pid = " + pi.getId() + ", bizKey = " + pi.getBusinessKey());

            processInstanceId = pi.getId();

            // 任务开始之前
            generateProcessFlowImage(processInstanceId, engine);

            Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
            taskService.createTaskQuery().processInstanceId(pi.getId()).list()
                    .forEach(task1 -> System.out.println("==>当前节点流程:" + task1.getName()));

            // 填写完成申请
            taskService.complete(task.getId());
            System.out.println("填写完成申请");
            taskService.createTaskQuery().processInstanceId(pi.getId()).list()
                    .forEach(task1 -> System.out.println("==>当前节点流程:" + task1.getName()));
            generateProcessFlowImage(processInstanceId, engine);

            // 经理审批
            task = taskService.createTaskQuery().processInstanceId(pi.getId()).taskName("经理审批").singleResult();
            taskService.complete(task.getId());
            System.out.println("经理完成审批");
            taskService.createTaskQuery().processInstanceId(pi.getId()).list()
                    .forEach(task1 -> System.out.println("==>当前节点流程:" + task1.getName()));
            generateProcessFlowImage(processInstanceId, engine);

        } finally {
            if (engine != null) {
                engine.close();
            }
            System.exit(0);
        }
    }

    // 本地生成流程图
    private void generateProcessFlowImage(String processInstanceId, ProcessEngine engine) throws IOException {
        // 生成图片
        InputStream inputStream = ActivitiUtil.getdDiagram(processInstanceId, engine);
        String yyyyMMddHHmmss = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String tempDir = FileUtil.getTempDir("img", "bpmn");
        System.out.println("tempDir = " + tempDir);
        FileOutputStream out = new FileOutputStream(
                new File(String.format("%s/%s-activiti-img.jpg", tempDir, yyyyMMddHHmmss)));
        StreamUtils.copy(inputStream, out);
        out.flush();
        out.close();
    }


}
