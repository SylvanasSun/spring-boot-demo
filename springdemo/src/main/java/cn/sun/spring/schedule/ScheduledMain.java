package cn.sun.spring.schedule;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by sylvanasp on 2016/7/23.
 */
public class ScheduledMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScheduledConfig.class);
        ScheduledTaskService taskService = context.getBean(ScheduledTaskService.class);
    }

}
