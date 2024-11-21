package com.elong.hotel.hotelmy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author bobo
 * @date 2024/11/21 16:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book implements Serializable{

    private static final long serialVersionUID = -659071005120318750L;
    private String name;
    private double price;
    private String author;
}
