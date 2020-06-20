package com.zk.activiti6.utils;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.util.List;

/**
 * Created by zhukai on 2020/6/20
 */
public class ClassUtilTest {

    @Test
    public void test() {
        String packageName = ClassUtil.class.getPackage().getName();
        List<String> clazzNameList = ClassUtil.getClazzName(packageName, false, false);
        Assert.assertTrue(clazzNameList.contains(ClassUtil.class.getName()));
    }

    @Test
    public void test2() {
        List<String> clazzNameList = ClassUtil.getClazzName("org.springframework", true, true);
        Assert.assertTrue(clazzNameList.contains(ResourceUtils.class.getName()));
    }

}
