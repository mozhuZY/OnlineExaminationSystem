package com.zy.oes.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @projectName: OnlineExaminationSystem
 * @className: RedisUtil
 * @author: MoZhu
 * @date: 2023/4/28 2:37
 * @description: <p> redis工具类 </p>
 */
@Component
public class RedisUtil {
    private StringRedisTemplate redisTemplate;

    @Autowired
    public RedisUtil(StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = stringRedisTemplate;
    }

    public StringRedisTemplate getTemplate() {
        return redisTemplate;
    }

    public ValueOperations<String, String> getOpsForValue() {
        return redisTemplate.opsForValue();
    }

    public void setTime(String key, Long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
}
