package com.zk.activiti6.utils;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhukai on 2020/4/12
 */
public class JsonUtilsTest {

    @Test
    public void test_isJsonStr() {
        User user = new User("张三", 18);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", "ok");
        map.put("data", user);

        // 验证数组普通字符串
        Assert.assertFalse(JsonUtils.isJsonStr("ok"));

        // 验证普通Json字符串
        Assert.assertTrue(JsonUtils.isJsonStr(JsonUtils.toJsonNotNullKey(map)));

        // 验证数组1
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3);
        String arr = JsonUtils.toJsonNotNullKey(list);
        Assert.assertTrue(JsonUtils.isJsonStr(arr));

        // 验证数组2
        ArrayList<User> list2 = Lists.newArrayList(user);
        String arr2 = JsonUtils.toJsonNotNullKey(list2);
        Assert.assertTrue(JsonUtils.isJsonStr(arr2));

        // 验证复杂json字符串
        map.put("arr", arr2);
        Assert.assertTrue(JsonUtils.isJsonStr(JsonUtils.toJsonNotNullKey(map)));

        // 其他情况验证
        Assert.assertTrue(JsonUtils.isJsonStr("{}"));
        Assert.assertTrue(JsonUtils.isJsonStr("[]"));
        Assert.assertTrue(JsonUtils.isJsonStr("[1]"));
        Assert.assertFalse(JsonUtils.isJsonStr("{"));
        Assert.assertFalse(JsonUtils.isJsonStr("["));
        Assert.assertFalse(JsonUtils.isJsonStr("{a}"));

    }

    @Test
    public void test_getValueDeeply() {
        User user = new User("张三", 18);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", "ok");
        map.put("data", user);
        String jsonStr = JsonUtils.toJsonNotNullKey(map);

        Assert.assertEquals(jsonStr, JsonUtils.getValueDeeply(jsonStr));
        Assert.assertEquals("ok", JsonUtils.getValueDeeply(jsonStr, "result"));
        Assert.assertEquals("张三", JsonUtils.getValueDeeply(jsonStr, "data", "name"));
        Assert.assertEquals("18", JsonUtils.getValueDeeply(jsonStr, "data", "age"));
        Assert.assertNull(JsonUtils.getValueDeeply(jsonStr, "data", "notExisted"));
    }

    @Test
    public void test_toMap() {
        User user = new User("张三", 18);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", "ok");
        map.put("data", user);
        String jsonStr = JsonUtils.toJsonNotNullKey(map);

        Map<String, Object> resultMap = JsonUtils.toMap(jsonStr);
        Assert.assertEquals("ok", resultMap.get("result"));
        Assert.assertEquals("张三", ((Map) resultMap.get("data")).get("name"));
        Assert.assertEquals(18, ((Map) resultMap.get("data")).get("age"));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class User {
        private String name;
        private int    age;
    }

}
