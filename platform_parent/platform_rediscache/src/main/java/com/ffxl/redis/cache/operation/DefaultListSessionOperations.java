package com.ffxl.redis.cache.operation;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 简单的ListSessionOperations
 */
public class DefaultListSessionOperations<V> extends DefaultSessionOperations implements ListSessionOperations<V> {

    public DefaultListSessionOperations(RedisTemplate<String, V> redisTemplate, String serviceName, String key) {
        super(redisTemplate, serviceName, key);
    }

    private BoundListOperations<String, V> getBoundOperations() {
        return this.redisTemplate.boundListOps(this.redisKey);
    }

    
    public RedisOperations<String, V> getOperations() {
        return this.getBoundOperations().getOperations();
    }

    
    public List<V> range(long start, long end) {
        return this.getBoundOperations().range(start, end);
    }

    
    public void trim(long start, long end) {
        this.getBoundOperations().trim(start, end);
    }

    
    public Long size() {
        return this.getBoundOperations().size();
    }

    
    public Long leftPush(V value) {
        return this.getBoundOperations().leftPush(value);
    }

    
    public Long leftPushAll(V... values) {
        return this.getBoundOperations().leftPushAll(values);
    }

    
    public Long leftPushIfPresent(V value) {
        return this.getBoundOperations().leftPushIfPresent(value);
    }

    
    public Long leftPush(V pivot, V value) {
        return this.getBoundOperations().leftPush(pivot, value);
    }

    
    public Long rightPush(V value) {
        return this.getBoundOperations().rightPush(value);
    }

    
    public Long rightPushAll(V... values) {
        return this.getBoundOperations().rightPushAll(values);
    }

    
    public Long rightPushIfPresent(V value) {
        return this.getBoundOperations().rightPushIfPresent(value);
    }

    
    public Long rightPush(V pivot, V value) {
        return this.getBoundOperations().rightPush(pivot, value);
    }

    
    public V leftPop() {
        return this.getBoundOperations().leftPop();
    }

    
    public V leftPop(long timeout, TimeUnit unit) {
        return this.getBoundOperations().leftPop(timeout, unit);
    }

    
    public V rightPop() {
        return this.getBoundOperations().rightPop();
    }

    
    public V rightPop(long timeout, TimeUnit unit) {
        return this.getBoundOperations().rightPop(timeout, unit);
    }

    
    public Long remove(long i, Object value) {
        return this.getBoundOperations().remove(i, value);
    }

    
    public V index(long index) {
        return this.getBoundOperations().index(index);
    }

    
    public void set(long index, V value) {
        this.getBoundOperations().set(index, value);
    }

    
    public String getKey() {
        return this.getBoundOperations().getKey();
    }

    
    public DataType getType() {
        return this.getBoundOperations().getType();
    }

    
    public Long getExpire() {
        return this.getBoundOperations().getExpire();
    }

    
    public Boolean expire(long timeout, TimeUnit unit) {
        return this.getBoundOperations().expire(timeout, unit);
    }

    
    public Boolean expireAt(Date date) {
        return this.getBoundOperations().expireAt(date);
    }

    
    public Boolean persist() {
        return this.getBoundOperations().persist();
    }

    @Deprecated
    
    public void rename(String newKey) {

    }
}
