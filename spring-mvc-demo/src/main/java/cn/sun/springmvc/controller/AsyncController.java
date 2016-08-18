package cn.sun.springmvc.controller;

import cn.sun.springmvc.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 异步任务的实现是通过控制器从另外一个线程返回一个DeferredResult。
 * Created by sylvanasp on 2016/7/25.
 */
@Controller
public class AsyncController {

    @Autowired
    private PushService pushService;

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredResult() {
        return pushService.getAsyncUpdate();
    }

}
