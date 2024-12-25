package com.elong.hotel.spring.obsever;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author bobo
 * @date 2024/12/23 15:03
 **/
@Component
public class PublisherEntity {

    @Bean
    CommandLineRunner runner(EventPublisher eventPublisher){
        System.out.println("hell");
        return args -> {
            eventPublisher.publishEvent(new CustomEvent(this, null, "hello---->>>>>>>>>>>>>>"));
        };
    }

    public void commandLineListener(){
        CommandLineRunner runner = args->{
            System.out.println("commandLineListener监听到了事件");
        };
    }
}
