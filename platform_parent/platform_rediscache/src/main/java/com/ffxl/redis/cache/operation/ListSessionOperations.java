package com.ffxl.redis.cache.operation;

import org.springframework.data.redis.core.BoundListOperations;

/**
 * ListSessionOperations
 */
public interface ListSessionOperations<V> extends BoundListOperations<String, V> {
}
