package com.ffxl.redis.cache.operation;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 简单的ValueSessionOperations
 */
public class DefaultValueSessionOperations extends DefaultSessionOperations implements ValueSessionOperations {

    public DefaultValueSessionOperations(RedisTemplate<String, Object> redisTemplate, String serviceName, String key) {
        super(redisTemplate, serviceName, key);
    }

    
    public RedisOperations getOperations() {
        return null;
    }

    
    public void set(Object value) {

    }

    
    public void set(Object value, long offset) {

    }

    
    public void set(Object value, long timeout, TimeUnit unit) {

    }

    
    public Boolean setIfAbsent(Object value) {
        return null;
    }

    
    public Object get() {
        return null;
    }

    
    public String get(long start, long end) {
        return null;
    }

    
    public Object getAndSet(Object value) {
        return null;
    }

    
    public Long increment(long delta) {
        return null;
    }

    
    public Double increment(double delta) {
        return null;
    }

    
    public Integer append(String value) {
        return null;
    }

    
    public Long size() {
        return null;
    }

    
    public Object getKey() {
        return null;
    }

    
    public DataType getType() {
        return null;
    }

    
    public Long getExpire() {
        return null;
    }

    
    public Boolean expire(long timeout, TimeUnit unit) {
        return null;
    }

    
    public Boolean expireAt(Date date) {
        return null;
    }

    
    public Boolean persist() {
        return null;
    }

    
    public void rename(Object newKey) {

    }
}
