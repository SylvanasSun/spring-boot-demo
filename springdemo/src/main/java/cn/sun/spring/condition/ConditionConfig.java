package cn.sun.spring.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sylvanasp on 2016/7/23.
 */
@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public WindowsBean windowsBean() {
        return new WindowsBean();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public LinuxBean linuxBean() {
        return new LinuxBean();
    }

}
