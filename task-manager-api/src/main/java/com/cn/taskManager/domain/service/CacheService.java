package com.cn.taskManager.domain.service;

import com.cn.taskManager.common.utils.ByteObjectUtils;
import com.cn.taskManager.common.utils.EscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 *
 * @author Evan
 * @date 2017年10月24日
 */
@Component
public class CacheService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${spring.redis.expire-time:1800}")
    private Long expireTime = 1800L;
    
    /**
     * 读取缓存
     *
     * @param key 键
     * @return
     */
    public Object get(Object key) {
        if (!this.containsKey(key)) {
            return null;
        }
        return this.redisTemplate.opsForValue().get(key);
    }
    
    /**
     * 判断缓存中是否有对应的key
     *
     * @param key 键
     * @return
     */
    public boolean containsKey(Object key) {
        return this.redisTemplate.hasKey(key);
    }
    
    /**
     * 删除对应的key
     *
     * @param key 键
     */
    public void remove(Object key) {
        if (this.containsKey(key)) {
            this.redisTemplate.delete(key);
        }
    }
    
    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean put(Object key, Object value) {
        return this.put(key, value, this.expireTime);
    }
    
    /**
     * 写入缓存并设置有效时间
     *
     * @param key          键
     * @param value        值
     * @param expireSecond 过期时间,单位秒
     * @return
     */
    public boolean put(Object key, Object value, Long expireSecond) {
        boolean result = false;
        try {
            this.redisTemplate.opsForValue().set(key, value, expireSecond, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 清空所有的键
     */
    public void clear() {
        Set<String> keys = this.stringRedisTemplate.keys("*");
        for (String key : keys) {
            this.stringRedisTemplate.delete(key);
        }
    }
    
    /**
     * 获取所有的key
     *
     * @return
     */
    public Set<Object> keySet() {
        Set<String> keys = this.stringRedisTemplate.keys("*");
        Set<Object> ks = new HashSet<>();
        for (String key : keys) {
            byte[] bs = EscapeUtils.decodeBase64(key);
            Object byteToObject = ByteObjectUtils.byteToObject(bs);
            ks.add(byteToObject);
        }
        return ks;
    }
}
