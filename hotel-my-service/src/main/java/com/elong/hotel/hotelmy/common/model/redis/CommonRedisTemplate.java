package com.elong.hotel.hotelmy.common.model.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author bobo
 * @date 2024/11/21 18:55
 **/
@Component
public class CommonRedisTemplate {

    @Autowired
    @Qualifier("stringRedisTemplate")
    RedisTemplate objectObjectRedisTemplate;

    public void putValue(String key,Object value){
        objectObjectRedisTemplate.opsForValue().append(key, String.valueOf(value));
    }

    public String getValue(String key){
        return (String) objectObjectRedisTemplate.opsForValue().get(key);
    }

    public void leftPush(String key,String ...values){
        objectObjectRedisTemplate.opsForSet().add(key,values);
    }

    public void opForZset(String key,String value,double score){
        objectObjectRedisTemplate.opsForZSet().add(key,value,score);
    }
}
