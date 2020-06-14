package com.zk.activiti6.activiti.custom;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.image.ProcessDiagramGenerator;

import java.awt.*;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * Created by zhukai on 2020/6/13
 */
public interface ICustomProcessDiagramGenerator extends ProcessDiagramGenerator {
    InputStream generateDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities,
                                List<String> highLightedFlows, String activityFontName, String labelFontName,
                                String annotationFontName, ClassLoader customClassLoader, double scaleFactor,
                                Color[] colors, Set<String> currIds);
}
