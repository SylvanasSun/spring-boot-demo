package cn.sun.springmvc.controller;

import cn.sun.springmvc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sylvanasp on 2016/7/25.
 */
@Controller
public class NormalController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/normal")
    public String testPage(Model model) {
        model.addAttribute("msg",demoService.saySomething());
        return "page";
    }

}
