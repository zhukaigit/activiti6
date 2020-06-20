package com.zk.activiti6.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhukai on 2020/6/20
 */
public class ConcurrentMapGeneratorTest {

    @Test
    public void testNewHashMapBuilder() {
        Map<String, Integer> map = ConcurrentHashMapGenerator.<String, Integer> builder().put("k1", 1).put("k2", 2)
                .build();
        Assert.assertTrue(map instanceof ConcurrentHashMap);
        Assert.assertEquals(1, (int) map.get("k1"));
        Assert.assertEquals(2, (int) map.get("k2"));
        Assert.assertEquals(2, map.size());
        map.put("k3", 3);
        Assert.assertEquals(3, (int) map.get("k3"));
        Assert.assertEquals(3, map.size());
    }

    @Test
    public void testOf() {
        Map<String, Integer> map = ConcurrentHashMapGenerator.of("k1", 1);
        Assert.assertTrue(map instanceof ConcurrentHashMap);
        Assert.assertEquals(1, (int) map.get("k1"));
        Assert.assertEquals(1, map.size());
    }

    @Test
    public void testOf2() {
        Map<String, Integer> map = ConcurrentHashMapGenerator.of("k1", 1, "k2", 2);
        Assert.assertTrue(map instanceof ConcurrentHashMap);
        Assert.assertEquals(1, (int) map.get("k1"));
        Assert.assertEquals(2, (int) map.get("k2"));
        Assert.assertEquals(2, map.size());
    }

    @Test
    public void testOf3() {
        Map<String, Integer> map = ConcurrentHashMapGenerator.of("k1", 1, "k2", 2, "k3", 3);
        Assert.assertTrue(map instanceof ConcurrentHashMap);
        Assert.assertEquals(1, (int) map.get("k1"));
        Assert.assertEquals(2, (int) map.get("k2"));
        Assert.assertEquals(3, (int) map.get("k3"));
        Assert.assertEquals(3, map.size());
    }

    @Test
    public void testOf4() {
        Map<String, Integer> map = ConcurrentHashMapGenerator.of("k1", 1, "k2", 2, "k3", 3, "k4", 4);
        Assert.assertTrue(map instanceof ConcurrentHashMap);
        Assert.assertEquals(1, (int) map.get("k1"));
        Assert.assertEquals(2, (int) map.get("k2"));
        Assert.assertEquals(3, (int) map.get("k3"));
        Assert.assertEquals(4, (int) map.get("k4"));
        Assert.assertEquals(4, map.size());
    }

}
