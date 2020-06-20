package com.zk.activiti6.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhukai on 2020/6/20
 */
public class HashMapGenerator {

    public static <K, V> Map<K, V> of(K k1, V v1) {
        return HashMapGenerator.<K, V> builder().put(k1, v1).build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        return HashMapGenerator.<K, V> builder().put(k1, v1).put(k2, v2).build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return HashMapGenerator.<K, V> builder().put(k1, v1).put(k2, v2).put(k3, v3).build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return HashMapGenerator.<K, V>builder().put(k1, v1).put(k2, v2).put(k3, v3).put(k4, v4).build();
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<K, V>(new HashMap(4));
    }

    public static class Builder<K, V> {
        private Map<K, V> map;

        private Builder(Map<K, V> map) {
            this.map = map;
        }

        public Builder<K, V> put(K key, V value) {
            map.put(key, value);
            return this;
        }

        public Builder<K, V> putAll(Map<K, V> m) {
            map.putAll(m);
            return this;
        }

        public Map<K, V> build() {
            return map;
        }

    }
}
