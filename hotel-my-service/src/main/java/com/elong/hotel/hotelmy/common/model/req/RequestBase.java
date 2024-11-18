package com.elong.hotel.hotelmy.common.model.req;

import lombok.Data;

/**
 * @author bobo
 */
@Data
public class RequestBase <T>{
    String channel;
    T realRequest;
}
