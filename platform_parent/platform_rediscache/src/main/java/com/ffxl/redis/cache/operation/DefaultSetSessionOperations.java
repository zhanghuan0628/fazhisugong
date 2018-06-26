package com.ffxl.redis.cache.operation;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 简单的SetSessionOperations
 */
public class DefaultSetSessionOperations extends DefaultSessionOperations implements SetSessionOperations {

    public DefaultSetSessionOperations(RedisTemplate<String, Object> redisTemplate, String serviceName, String key) {
        super(redisTemplate, serviceName, key);
    }

    
    public RedisOperations getOperations() {
        return null;
    }

    
    public Set diff(Object key) {
        return null;
    }

    
    public Set diff(Collection keys) {
        return null;
    }

    
    public void diffAndStore(Object key, Object destKey) {

    }

    
    public void diffAndStore(Collection keys, Object destKey) {

    }

    
    public Set intersect(Object key) {
        return null;
    }

    
    public Set intersect(Collection keys) {
        return null;
    }

    
    public void intersectAndStore(Object key, Object destKey) {

    }

    
    public void intersectAndStore(Collection keys, Object destKey) {

    }

    
    public Set union(Object key) {
        return null;
    }

    
    public Set union(Collection keys) {
        return null;
    }

    
    public void unionAndStore(Object key, Object destKey) {

    }

    
    public void unionAndStore(Collection keys, Object destKey) {

    }

    
    public Long add(Object[] values) {
        return null;
    }

    
    public Boolean isMember(Object o) {
        return null;
    }

    
    public Set members() {
        return null;
    }

    
    public Boolean move(Object destKey, Object value) {
        return null;
    }

    
    public Object randomMember() {
        return null;
    }

    
    public Set distinctRandomMembers(long count) {
        return null;
    }

    
    public List randomMembers(long count) {
        return null;
    }

    
    public Long remove(Object... values) {
        return null;
    }

    
    public Object pop() {
        return null;
    }

    
    public Long size() {
        return null;
    }

    
    public Cursor scan(ScanOptions options) {
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
