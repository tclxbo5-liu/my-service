package com.elong.hotel.spring.obsever;

import org.springframework.context.ApplicationListener;

/**
 * @author bobo
 * @date 2024/12/23 15:00
 **/
public class MyActionListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("MyActionListener监听到了事件"+event.getMessage());
    }
}
