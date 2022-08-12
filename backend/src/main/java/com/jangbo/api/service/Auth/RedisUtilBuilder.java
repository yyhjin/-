package com.jangbo.api.service.Auth;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisUtilBuilder {
    private StringRedisTemplate stringRedisTemplate;
    private RedisTemplate<String, Object> redisBlackListTemplate;

    public RedisUtilBuilder setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        return this;
    }

    public RedisUtilBuilder setRedisBlackListTemplate(RedisTemplate<String, Object> redisBlackListTemplate) {
        this.redisBlackListTemplate = redisBlackListTemplate;
        return this;
    }

    public RedisUtil createRedisUtil() {
        return new RedisUtil(stringRedisTemplate, redisBlackListTemplate);
    }
}