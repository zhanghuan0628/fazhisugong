package com.ffxl.redis.cache.operation;

import org.springframework.data.redis.core.BoundHashOperations;

/**
 * HashSessionOperations
 */
public interface HashSessionOperations<HK, HV> extends BoundHashOperations<String, HK, HV> {
}
