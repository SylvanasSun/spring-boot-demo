package cn.sun.spring.schedule;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by sylvanasp on 2016/7/23.
 */
@Configuration
@ComponentScan("cn.sun.spring.schedule")
@EnableScheduling
public class ScheduledConfig {
}
