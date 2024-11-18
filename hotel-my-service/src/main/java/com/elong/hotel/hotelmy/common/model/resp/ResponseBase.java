package com.elong.hotel.hotelmy.common.model.resp;

import lombok.Data;

/**
 * @author bobo
 */
@Data
public class ResponseBase<T>{
    int responseCode;
    String message;
    T data;
}
