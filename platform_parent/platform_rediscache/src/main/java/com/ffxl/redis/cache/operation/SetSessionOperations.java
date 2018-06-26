package com.ffxl.redis.cache.operation;

import org.springframework.data.redis.core.BoundSetOperations;

/**
 * SetSessionOperations
 */
public interface SetSessionOperations<K, V> extends BoundSetOperations<K, V> {
}
