package com.zk.activiti6.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Created by zhukai on 2020/6/14
 */
@Slf4j
public class FileUtil {

    /**
     * 创建文件,存在则不创建
     * 
     * @param fileAbsolutePath 文件绝对路径
     * @return
     */
    public static File createFileIfNotExisted(String fileAbsolutePath) {
        File file = new File(fileAbsolutePath);
        if (file.exists() && file.isFile()) {
            return file;
        }
        // 创建文件目录
        createDirIfNotExisted(file.getParentFile().getAbsolutePath());
        // 创建文件
        boolean newFile = false;
        try {
            newFile = file.createNewFile();
        } catch (Exception e) {
            log.error("文件创建失异常,fileAbsolutePath={}", fileAbsolutePath, e);
            throw new RuntimeException("文件创建失异常");
        }
        AssertUtil.assertTrue(newFile, fileAbsolutePath + "文件创建失败");
        return file;
    }

    /**
     * 创建目录,存在则不创建
     * 
     * @param dirPath 目录路径
     * @return
     */
    public static File createDirIfNotExisted(String dirPath) {
        File file = new File(dirPath);
        if (file.exists() && file.isDirectory()) {
            return file;
        }
        boolean mkdirs = false;
        try {
            mkdirs = file.mkdirs();
        } catch (Exception e) {
            log.error("创建目录异常,dirPath={}", dirPath, e);
            throw new RuntimeException(dirPath + "目录创建异常");
        }
        AssertUtil.assertTrue(mkdirs, "目录创建失败,dirPath=" + dirPath);
        return new File(dirPath);
    }

    /**
     * 获取临时目录,若subDirName参数不为空,则在该临时目录下创建子文件夹,并返回该文件夹路径
     * 
     * @param subDirNames 临时目录下的子目录名称
     * @return
     */
    public static String getTempDir(String... subDirNames) {
        String temDir = System.getProperty("java.io.tmpdir");
        StringBuilder resultDir = new StringBuilder(temDir.endsWith(File.separator) ? temDir : temDir + File.separator);
        if (subDirNames != null && subDirNames.length > 0) {
            for (String subDirName : subDirNames) {
                // 校验子文件名中是否有目录分割符
                AssertUtil.assertTrue(!subDirName.contains(File.separator), subDirName + "子文件名中不能有目录分割符");
                resultDir.append(subDirName).append(File.separator);
            }
        }
        String path = createDirIfNotExisted(resultDir.toString()).getAbsolutePath();
        return path.endsWith(File.separator) ? path : path + File.separator;
    }

}
