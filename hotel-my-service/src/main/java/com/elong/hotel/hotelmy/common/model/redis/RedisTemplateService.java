package com.elong.hotel.hotelmy.common.model.redis;

import com.alibaba.fastjson.JSON;
import com.elong.hotel.hotelmy.entity.Book;
import com.elong.hotel.hotelmy.respositorys.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author bobo
 * @date 2024/11/21 16:08
 **/
@Component
public class RedisTemplateService {

    @Autowired
    RedisTemplate redisTemplate;

    public static final String CACHE="cache";

    public static final Logger LOGGER= LoggerFactory.getLogger(RedisTemplateService.class.getName());

    @Autowired
    BookRepository bookRepository;
//
//    @Bean
//    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        return redisTemplate;
//    }

    public Optional<Book> findOneBook(String name){
        HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, name)) {
            LOGGER.info(String.format("get book() from redis %s", name));
            String s = hashOperations.get(CACHE, name);
            System.out.println(s);
            return Optional.of(JSON.parseObject(s,Book.class));
        }
        Optional<Book> book = bookRepository.getBook(name);
        if (book.isPresent()){
            Book bookInfo = book.get();
            hashOperations.put(CACHE,name, JSON.toJSONString(bookInfo));
            redisTemplate.expire(CACHE,10, TimeUnit.SECONDS);
        }
        return book;
    }
}
