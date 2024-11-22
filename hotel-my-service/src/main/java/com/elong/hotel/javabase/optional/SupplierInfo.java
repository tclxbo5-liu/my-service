package com.elong.hotel.javabase.optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.function.Supplier;

/**
 * @author bobo
 * @date 2024/11/21 19:54
 **/
@Data
@Builder
@AllArgsConstructor
public class SupplierInfo implements Supplier {

    String name;

    @Override
    public Object get() {
        return this.name;
    }
}
