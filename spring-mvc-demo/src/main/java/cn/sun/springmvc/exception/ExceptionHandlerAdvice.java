package cn.sun.springmvc.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sylvanasp on 2016/7/24.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest webRequest){
        ModelAndView modelAndView = new ModelAndView("error");// error页面
        modelAndView.addObject("errorMessage",exception.getMessage());
        return modelAndView;
    }

    /**
     * @ModelAttribute注解将键值对添加到全局,所有注解的@RequestMapping的方法都可获得此键值对
     */
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg","额外信息");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        // 忽略request参数的id属性
        webDataBinder.setDisallowedFields("id");
    }

}
