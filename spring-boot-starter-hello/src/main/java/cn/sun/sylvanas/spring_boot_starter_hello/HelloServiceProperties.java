package cn.sun.sylvanas.spring_boot_starter_hello;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 类型安全的属性获取,在application.properties中通过hello.msg= 来设置.
 * 若不设置,默认为hello.msg=world.
 * Created by sylvanasp on 2016/7/26.
 */
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {

    private static final String MSG = "world";

    private String msg = MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
