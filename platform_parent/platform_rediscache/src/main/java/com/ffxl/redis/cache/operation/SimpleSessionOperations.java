package com.ffxl.redis.cache.operation;

/**
 * SimpleSessionOperations
 */
public interface SimpleSessionOperations<V> {

    V get(String group, String item);

    void put(String group, String item, V value);
}
