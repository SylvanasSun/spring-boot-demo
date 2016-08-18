package cn.sun.sylvanas.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * user.properties关联Bean
 * @ConfigurationProperties可以指定关联properties文件
 * prefix属性指定properties的配置的前缀
 * locations属性指定properties文件的位置
 * Created by sylvanasp on 2016/7/25.
 */
@Component
@ConfigurationProperties(prefix = "user")
public class UserSettings {

    private String name;
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
