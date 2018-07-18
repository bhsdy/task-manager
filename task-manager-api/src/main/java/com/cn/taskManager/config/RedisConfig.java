package com.cn.taskManager.config;


import com.cn.taskManager.common.utils.ByteObjectUtils;
import com.cn.taskManager.common.utils.EscapeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * redis配置
 *
 * @author evan
 */
@Configuration
public class RedisConfig {
    @Value("${spring.redis.expire-time:1800}")
    private Long expireTime = 1800L;
    
    /**
     * 对象缓存器
     *
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<Object> base64RedisSerializer = new RedisSerializer<Object>() {
            
            @Override
            public byte[] serialize(Object t) throws SerializationException {
                byte[] bs = ByteObjectUtils.objectToByte(t);
                String base64 = EscapeUtils.encodeBase64(bs);
                return base64.getBytes(Charset.forName("UTF-8"));
            }
            
            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                String base64 = new String(bytes, Charset.forName("UTF-8"));
                byte[] bs = EscapeUtils.decodeBase64(base64);
                return ByteObjectUtils.byteToObject(bs);
            }
        };
        template.setKeySerializer(base64RedisSerializer);
        template.setValueSerializer(base64RedisSerializer);
        return template;
    }
    
    /**
     * 字符串缓存器
     *
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        return template;
    }
    
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(expireTime);
        return cacheManager;
    }
}
