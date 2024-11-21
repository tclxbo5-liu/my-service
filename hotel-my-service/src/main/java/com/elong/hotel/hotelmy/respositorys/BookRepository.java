package com.elong.hotel.hotelmy.respositorys;

import com.elong.hotel.hotelmy.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author bobo
 * @date 2024/11/21 16:19
 **/
@Repository
public class BookRepository {
    Map<String, Book> bookMap = new HashMap<>();

    public BookRepository(){
        bookMap.put("kafka",Book.builder().name("kafka").price(12d).author("bobo").build());
        bookMap.put("python",Book.builder().name("python").price(12.3d).author("dog lea").build());
    }

    public Optional<Book> getBook(String name){
        if (bookMap.containsKey(name)) {
            return Optional.of(bookMap.get(name));
        } else {
            return Optional.empty();
        }
    }

}
