package com.ffxl.redis.cache.caches;

import com.ffxl.redis.cache.annotation.Description;
import com.ffxl.redis.cache.operation.HashSessionOperations;

@Description(value = "通用缓存客户端", sort = 1)
public interface PlatformCaches extends HashSessionOperations {
    
}

