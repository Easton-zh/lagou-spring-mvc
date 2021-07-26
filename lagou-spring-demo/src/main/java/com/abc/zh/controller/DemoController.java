package com.abc.zh.controller;

import com.abc.zh.pojo.QueryVo;
import com.abc.zh.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoController {

    /****************************************************************************************
     * springmvc正常demo
     * http://localhost:8080/demo/handle01
     */
    @RequestMapping("/handle01")
    public ModelAndView handle01() {
        System.out.println("进入处理器！！！");
        Date date = new Date();//获取服务器时间
        //返回前端页面
        //ModelAndView封装了数据库和页面信息
        ModelAndView modelAndView = new ModelAndView();
        //addObject()，其实是向请求域中request.setAttribute("date", date);
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * **********************************【数据输出机制】******************************************************
     * SpringMVC在handler方法上传入Map、Model和ModelMap参数，并向这些参数中保存数据（放入到请求域），都可以在页面获取到
     *
     * 它们之间是什么关系？
     * 运行时的具体类型都是 BindingAwareModelMap，相当于给BindingAwareModelMap中保存的数据都会放在请求域中
     *
     *  Map(jdk中的接口)        Model（spring框架的接口）      ModelMap(class,实现Map接口)
     *
     * BindingAwareModelMap 继承了ExtendedModelMap，ExtendedModelMap继承了ModelMap,实现了Model接口
     *
     */

    /**
     * 测试ModelMap
     * @param modelMap
     * @return
     */
    @RequestMapping("/handle001")
    public String handle001(ModelMap modelMap) {
        Date date = new Date();//获取服务器时间
        modelMap.addAttribute("date", date);
        return "success";
    }

    /**
     * 测试Model
     * @param model
     * @return
     */
    @RequestMapping("/handle002")
    public String handle002(Model model) {
        Date date = new Date();//获取服务器时间
        model.addAttribute("date", date);
        return "success";
    }

    /**
     * 测试Map
     * @param map
     * @return
     */
    @RequestMapping("/handle003")
    public String handle003(Map<String, Object> map) {
        Date date = new Date();//获取服务器时间
        map.put("date", date);
        return "success";
    }

    /******************************【SpringMVC如何接收请求参数】************************************************************
     *
     * SpringMVC 对原生servlet api的支持  url：/demo/handle02?id=1
     *
     * 如果要在SpringMVC中使用servlet原生对象，比如HttpServletRequest\HttpServletResponse\HttpSession，直接在Handler方法形参中声明使用即可
     *
     */
    @RequestMapping("/handle02")
    public ModelAndView handle02(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String id = request.getParameter("id");

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /*
     * SpringMVC 接收简单数据类型参数  url：/demo/handle03?id=1
     *
     * 注意：接收简单数据类型参数，直接在handler方法的形参中声明即可，框架会取出参数值然后绑定到对应参数上
     * 要求：传递的参数名和声明的形参名称保持一致
     */
//    @RequestMapping("/handle03")
//    public ModelAndView handle03(Integer id) {
//
//        Date date = new Date();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("date",date);
//        modelAndView.setViewName("success");
//        return modelAndView;
//    }

    /*
     * SpringMVC 接收简单数据类型参数  url：/demo/handle03?id=1
     *
     * 注意：接收简单数据类型参数，直接在handler方法的形参中声明即可，框架会取出参数值然后绑定到对应参数上
     * 要求：传递的参数名和声明的形参名称保持一致
     */
    @RequestMapping("/handle03")
    public ModelAndView handle03(@RequestParam("ids") Integer id, Boolean flag) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*
     * SpringMVC接收pojo类型参数  url：/demo/handle04?id=1&username=zhangsan
     *
     * 接收pojo类型参数，直接形参声明即可，类型就是Pojo的类型，形参名无所谓
     * 但是要求传递的参数名必须和Pojo的属性名保持一致
     */
    @RequestMapping("/handle04")
    public ModelAndView handle04(User user) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*
     * SpringMVC接收pojo包装类型参数  url：/demo/handle05?user.id=1&user.username=zhangsan
     * 不管包装Pojo与否，它首先是一个pojo，那么就可以按照上述pojo的要求来
     * 1、绑定时候直接形参声明即可
     * 2、传参参数名和pojo属性保持一致，如果不能够定位数据项，那么通过属性名 + "." 的方式进一步锁定数据
     *
     */
    @RequestMapping("/handle05")
    public ModelAndView handle05(QueryVo queryVo) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /**
     * 绑定日期类型参数:
     * 解决方案：定义一个SpringMVC的类型转换器  接口，扩展实现接口接口，注册你的实现
     * @param birthday
     * @return
     */
    @RequestMapping("/handle06")
    public ModelAndView handle06(Date birthday) {
        Date date = new Date();ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }



    /*******************************【SpringMVC的restful风格】************************************************************
     * restful  get   /demo/handle/15
     */
    @RequestMapping(value = "/handle/{id}",method = {RequestMethod.GET})
    public ModelAndView handleGet(@PathVariable("id") Integer id) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /*
     * restful  post  /demo/handle
     */
    @RequestMapping(value = "/handle",method = {RequestMethod.POST})
    public ModelAndView handlePost(String username) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /*
     * restful  put  /demo/handle/15/lisi
     */
    @RequestMapping(value = "/handle/{id}/{name}",method = {RequestMethod.PUT})
    public ModelAndView handlePut(@PathVariable("id") Integer id,@PathVariable("name") String username) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /*
     * restful  delete  /demo/handle/15
     */
    @RequestMapping(value = "/handle/{id}",method = {RequestMethod.DELETE})
    public ModelAndView handleDelete(@PathVariable("id") Integer id) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*********************************【 Ajax Json交互】************************************************************
     * 添加@ResponseBody之后，不再走视图解析器那个流程，而是等同于response直接输出数据
     * @param user
     * @return
     */
    @RequestMapping("/handle07")
//    @ResponseBody TODO 都可以
    public @ResponseBody User handle07(@RequestBody User user) {

        // 业务逻辑处理，修改name为张三丰
        user.setName("张三丰");
        return user;
    }

}
