package com.ffxl.redis.cache.operation;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 简单的HashSessionOperations
 */
public class DefaultHashSessionOperations<HK, HV> extends DefaultSessionOperations implements HashSessionOperations<HK, HV> {

    public DefaultHashSessionOperations(RedisTemplate<String, HV> redisTemplate, String serviceName, String key) {
        super(redisTemplate, serviceName, key);
    }

    private BoundHashOperations<String, HK, HV> getBoundOperations() {
        return this.redisTemplate.boundHashOps(this.redisKey);
    }

    public RedisOperations getOperations() {
        return this.getBoundOperations().getOperations();
    }

    
    public Boolean hasKey(Object key) {
        return this.getBoundOperations().hasKey(key);
    }

    
    public Long increment(HK key, long delta) {
        return this.getBoundOperations().increment(key, delta);
    }

    
    public Double increment(HK key, double delta) {
        return this.getBoundOperations().increment(key, delta);
    }

    
    public HV get(Object key) {
        return this.getBoundOperations().get(key);
    }

    
    public void put(HK key, HV value) {
        this.getBoundOperations().put(key, value);
    }

    
    public Boolean putIfAbsent(HK key, HV value) {
        return this.getBoundOperations().putIfAbsent(key, value);
    }

    
    public List<HV> multiGet(Collection<HK> keys) {
        return this.getBoundOperations().multiGet(keys);
    }

    
    public void putAll(Map<? extends HK, ? extends HV> m) {
        this.getBoundOperations().putAll(m);
    }

    
    public Set<HK> keys() {
        return this.getBoundOperations().keys();
    }

    
    public List<HV> values() {
        return this.getBoundOperations().values();
    }

    
    public Long size() {
        return this.getBoundOperations().size();
    }

    
    public void delete(Object... keys) {
        this.getBoundOperations().delete(keys);
    }

    
    public Map<HK, HV> entries() {
        return this.getBoundOperations().entries();
    }

    
    public Cursor<Map.Entry<HK, HV>> scan(ScanOptions options) {
        return this.getBoundOperations().scan(options);
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
