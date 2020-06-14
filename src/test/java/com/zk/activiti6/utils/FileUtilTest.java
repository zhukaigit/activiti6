package com.zk.activiti6.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

/**
 * Created by zhukai on 2020/6/14
 */
public class FileUtilTest {

    @Test
    public void testCreateFileIfNotExisted() {
        String result = FileUtil.getTempDir(UUID.randomUUID().toString());
        File file = FileUtil.createFileIfNotExisted(result + UUID.randomUUID().toString() + ".jpg");
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.isFile());
        Assert.assertTrue(file.delete());
    }

    @Test
    public void testCreateDirIfNotExisted() {
        // 测试已存在的目录
        String dir = FileUtil.getTempDir("subDirName");
        File file = FileUtil.createDirIfNotExisted(dir);
        Assert.assertTrue(file.exists() && file.isDirectory());

        // 测试不存在的目录
        String dir2 = FileUtil.getTempDir() + UUID.randomUUID().toString();
        File file2 = FileUtil.createDirIfNotExisted(dir2);
        Assert.assertTrue(file2.exists() && file2.isDirectory());
    }

    @Test
    public void testGetTempDir() {
        String result = FileUtil.getTempDir("subDirName", "img");
        System.out.println("创建的目录:" + result);
        Assert.assertTrue(new File(result).isDirectory());
    }
}
