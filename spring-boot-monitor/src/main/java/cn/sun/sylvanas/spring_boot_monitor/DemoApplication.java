package cn.sun.sylvanas.spring_boot_monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册端点并定义演示控制器
 * Created by sylvanasp on 2016/8/4.
 */
@RestController
@SpringBootApplication
public class DemoApplication {

    @Autowired
    StatusService statusService;

    @Bean
    public Endpoint<String> status() {
        StatusEndpoint status = new StatusEndpoint();
        return status;
    }

    @RequestMapping("/change")
    public String changeStatus(String status) {
        statusService.setStatus(status);
        return "OK";
    }

}
