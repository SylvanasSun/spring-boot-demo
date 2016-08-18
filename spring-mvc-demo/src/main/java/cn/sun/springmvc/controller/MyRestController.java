package cn.sun.springmvc.controller;

import cn.sun.springmvc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sylvanasp on 2016/7/25.
 */
@Controller
public class MyRestController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "testRest", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String testRest() {
        return demoService.saySomething();
    }

}
