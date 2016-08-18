package cn.sun.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sylvanasp on 2016/7/23.
 */
@Controller
public class SimpleController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
