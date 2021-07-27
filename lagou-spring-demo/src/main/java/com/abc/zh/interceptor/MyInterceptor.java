package com.abc.zh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 自定义springmvc拦截器: 编写完实现需要去springmvc配置文件进行注册
 * @author hao.z
 * @version 1.0
 */
public class MyInterceptor implements HandlerInterceptor {


    /**
     * @description 会在handle方法业务逻辑之前执行
     * 使用场景：权限校验
     * @param request
     * @param response
     * @param handler
     * @return boolean 返回值代表是否方形，true放行，false终止
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor   preHandle()........");
        return true;
    }

    /**
     * 会在handler方法业务逻辑执行之后尚未跳转页面时执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView 封装了视图和数据，此时尚未跳转页面，可以在这里针对返回的数据和视图信息进行修改。（用很少）
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor   postHandle()........");
    }

    /**
     * 页面已经跳转渲染完毕后执行
     * @param request
     * @param response
     * @param handler
     * @param ex 可以在这里捕获异常（用的也比较少， 因为后面有拦截器）
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor   afterCompletion()........");
    }
}
