package cn.sun.spring.test;

import cn.sun.spring.asyn.AsynTaskService;
import cn.sun.spring.asyn.TaskExcutorConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by sylvanasp on 2016/7/22.
 */
public class AsyncTest {

    @Test
    public void testAsync() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExcutorConfig.class);
        AsynTaskService asynTaskService = context.getBean(AsynTaskService.class);
        for(int i=0; i < 10; i++){
            asynTaskService.asynExecute(i);
            asynTaskService.asynExecutePlus(i);
        }
        context.close();
    }
}
