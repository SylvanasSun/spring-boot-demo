package cn.sun.springmvc.controller;

import cn.sun.springmvc.pojo.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sylvanasp on 2016/7/24.
 */
@Controller
public class ConverterController {

    @RequestMapping(value = "/converter",produces = {"application/x-wisely"})
    @ResponseBody
    public DemoObj convert(@RequestBody DemoObj demoObj){
        return demoObj;
    }

}
