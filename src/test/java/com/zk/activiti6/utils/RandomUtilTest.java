package com.zk.activiti6.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhukai on 2020/6/20
 */
public class RandomUtilTest {

    @Test
    public void testGetRandomScope() throws Exception {
        int min = -1000;
        int max = 1000;
        for (int i = 0; i < 100000; i++) {
            int result = RandomUtil.getRandomScope(min, max);
            Assert.assertTrue(result >= min);
            Assert.assertTrue(result < max);
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
