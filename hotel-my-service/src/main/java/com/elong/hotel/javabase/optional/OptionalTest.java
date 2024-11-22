package com.elong.hotel.javabase.optional;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author bobo
 * @date 2024/11/21 19:49
 **/
public class OptionalTest {
    public static void main(String[] args) {
        String str = "gta5";
        Optional<String> optionalS= Optional.of(str);
        System.out.println(optionalS.get());
        String str2 = null;
        Optional optional = Optional.empty();
        System.out.println(optional.orElse("hell"));
        SupplierInfo supplierInfo = SupplierInfo.builder().name("bo5.liu").build();
        System.out.println(optional.orElseGet(supplierInfo));
        System.out.println(optional.orElseGet(() -> "Generated value"));
    }
}
