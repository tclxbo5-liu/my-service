package com.elong.hotel.hotelmy.demos.web.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author bobo
 * @date 2024/11/15 15:27
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Model model) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

}
