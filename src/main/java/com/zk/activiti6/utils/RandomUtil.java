package com.zk.activiti6.utils;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by zhukai on 2020/4/19
 */
public class RandomUtil {

    /**
     * 获取指定范围的随机数
     * @param min 最小值
     * @param max 最大值
     * @return 返回结果大于等于min，小于max
     */
    public static int getRandomScope(int min, int max) {
        SecureRandom s = new SecureRandom();
        s.setSeed(UUID.randomUUID().toString().getBytes(Charset.forName("utf-8")));
        int i = s.nextInt((max - min));// 0 <= i < (max-min)
        return i + min;
    }
}
