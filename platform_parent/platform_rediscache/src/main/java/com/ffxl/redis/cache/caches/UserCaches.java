package com.ffxl.redis.cache.caches;

import com.ffxl.redis.cache.annotation.Description;
import com.ffxl.redis.cache.operation.HashSessionOperations;

@Description(value = "用户缓存客户端", sort = 1)
public interface UserCaches extends HashSessionOperations {
    
}

