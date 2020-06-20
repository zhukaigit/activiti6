package com.zk.activiti6.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class AssertUtil {

    public static void notBlank(String data, String errMsg) {
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException(errMsg);
        }
    }

    public static void notNull(Object data, String errMsg) {
        if (data == null) {
            throw new RuntimeException(errMsg);
        }
    }

    public static void notEmpty(Collection coll, String errMsg) {
        if (CollectionUtils.isEmpty(coll)) {
            throw new RuntimeException(errMsg);
        }
    }

    public static void assertTrue(boolean condition, String errMsg) {
        if (!condition) {
            throw new RuntimeException(errMsg);
        }
    }
}
