package com.ffxl.redis.cache.operation;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 简单的ZSetSessionOperations
 */
public class DefaultZSetSessionOperations extends DefaultSessionOperations implements ZSetSessionOperations {

    public DefaultZSetSessionOperations(RedisTemplate<String, Object> redisTemplate, String serviceName, String key) {
        super(redisTemplate, serviceName, key);
    }

    
    public RedisOperations getOperations() {
        return null;
    }

    
    public void intersectAndStore(Object otherKey, Object destKey) {

    }

    
    public void intersectAndStore(Collection otherKeys, Object destKey) {

    }

    
    public Set range(long start, long end) {
        return null;
    }

    
    public Set rangeByScore(double min, double max) {
        return null;
    }

    
    public Set reverseRange(long start, long end) {
        return null;
    }

    
    public Set reverseRangeByScore(double min, double max) {
        return null;
    }

    
    public Set<ZSetOperations.TypedTuple> rangeWithScores(long start, long end) {
        return null;
    }

    
    public Set<ZSetOperations.TypedTuple> rangeByScoreWithScores(double min, double max) {
        return null;
    }

    
    public Set<ZSetOperations.TypedTuple> reverseRangeWithScores(long start, long end) {
        return null;
    }

    
    public Set<ZSetOperations.TypedTuple> reverseRangeByScoreWithScores(double min, double max) {
        return null;
    }

    
    public void removeRange(long start, long end) {

    }

    
    public void removeRangeByScore(double min, double max) {

    }

    
    public void unionAndStore(Object otherKey, Object destKey) {

    }

    
    public void unionAndStore(Collection otherKeys, Object destKey) {

    }

    
    public Boolean add(Object value, double score) {
        return null;
    }

    
    public Double incrementScore(Object value, double delta) {
        return null;
    }

    
    public Long rank(Object o) {
        return null;
    }

    
    public Long reverseRank(Object o) {
        return null;
    }

    
    public Long remove(Object... values) {
        return null;
    }

    
    public Long count(double min, double max) {
        return null;
    }

    
    public Long size() {
        return null;
    }

    
    public Long zCard() {
        return null;
    }

    
    public Double score(Object o) {
        return null;
    }

    
    public Cursor<ZSetOperations.TypedTuple> scan(ScanOptions options) {
        return null;
    }

    
    public Long add(Set set) {
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
