package com.elong.hotel.spring.obsever;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @author bobo
 * @date 2024/12/23 14:59
 **/


public class CustomEvent extends ApplicationEvent {

    private static final long serialVersionUID = 6352518055162043193L;
    String message;

    public String getMessage() {
        return message;
    }

    public CustomEvent(Object source, Clock clock, String message) {
        super(source, clock);
        this.message = message;
    }
}
