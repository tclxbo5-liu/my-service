package com.elong.hotel.hotelmy.demos.web.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bobo
 * @date 2024/11/15 15:28
 **/
@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // 返回自定义错误视图
        return "error";
    }
}
