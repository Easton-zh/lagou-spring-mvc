package com.abc.zh.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 可以优雅的捕获到所有controller对象handler方法抛出的异常
 */
@ControllerAdvice
public class GlobalExceptionResolver {


    @ExceptionHandler(ArithmeticException.class)
    public void HandlerException(ArithmeticException exception, HttpServletResponse response) {
        //异常处理逻辑
        try {
            response.getWriter().write(exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到异常页面
     * @param exception
     * @param response
     */
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView ModelAndView(ArithmeticException exception, HttpServletResponse response) {
        //异常处理逻辑
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
