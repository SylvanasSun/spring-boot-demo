package cn.sun.sylvanas.spring_boot_security.web;

import cn.sun.sylvanas.spring_boot_security.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 为home.html展示数据
 * Created by sylvanasp on 2016/8/2.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息,只对ADMIN展示!");
        model.addAttribute("msg", msg);
        return "home";
    }

}
