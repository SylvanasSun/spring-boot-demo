package cn.sun.spring.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by sylvanasp on 2016/7/23.
 */
public class ConditionMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ShowListService listService = context.getBean(ShowListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name") + "系统下的列表命令为: "
                + listService.showListCmd());
        context.close();
    }

}
