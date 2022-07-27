
package com.spring.demo.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class RedisService {
    @Resource
    private RedisTemplate<Object, String> redisTemplate;

    //添
    public <K, V> void set(K key, V value) {
        try {
            if (value != null) {
                redisTemplate
                        .opsForValue()
                        .set(key, JSONObject.toJSONString(value));
            }
        } catch (Exception e) {
            throw new RuntimeException("数据缓存至redis失败");
        }
    }

    // 获取
    public <K> String get(K key) {
        String value;
        try {
            value = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            throw new RuntimeException("从redis缓存中获取缓存数据失败");
        }
        return value;
    }


}
