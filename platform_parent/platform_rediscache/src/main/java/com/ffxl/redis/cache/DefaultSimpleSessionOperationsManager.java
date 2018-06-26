package com.ffxl.redis.cache;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.ffxl.redis.cache.operation.DefaultSessionOperations;
import com.ffxl.redis.cache.operation.SimpleSessionOperations;


/**
 * 简单的SessionOperationsManager
 */
public class DefaultSimpleSessionOperationsManager<V> extends DefaultSessionOperations implements SimpleSessionOperations<V> {

    public DefaultSimpleSessionOperationsManager(RedisTemplate<String, V> redisTemplate, String serviceName) {
        super(redisTemplate, serviceName, "SIMPLE");
    }

    private BoundHashOperations<String, String, V> getBoundOperations() {
        return this.redisTemplate.boundHashOps(this.redisKey);
    }

    public V get(String group, String item) {
        return this.getBoundOperations().get(String.format("%s_%s", group, item));
    }

    public void put(String group, String item, V value) {
        this.getBoundOperations().put(String.format("%s_%s", group, item), value);
    }
}
