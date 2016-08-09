package cn.sun.sylvanas;

import cn.sun.sylvanas.config.UserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootDemoApplication {

    @Autowired
    private UserSettings userSettings;

    @RequestMapping("/index")
    public String index() {
        return "Hello" + userSettings.getName() + "Welcome to Spring Boot World";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
