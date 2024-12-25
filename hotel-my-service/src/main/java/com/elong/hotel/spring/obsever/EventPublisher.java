package com.elong.hotel.spring.obsever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author bobo
 * @date 2024/12/23 14:58
 **/
@Component
public class EventPublisher {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent( CustomEvent customEvent){
        applicationEventPublisher.publishEvent(customEvent);
    }
}
