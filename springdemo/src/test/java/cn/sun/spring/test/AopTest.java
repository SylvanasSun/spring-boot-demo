package cn.sun.spring.test;

import cn.sun.spring.aop.AopConfig;
import cn.sun.spring.aop.DemoAnnotationService;
import cn.sun.spring.aop.DemoMethodService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by sylvanasp on 2016/7/22.
 */
public class AopTest {

    @Test
    public void testAop() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        DemoAnnotationService annotationService = context.getBean(DemoAnnotationService.class);

        DemoMethodService methodService = context.getBean(DemoMethodService.class);

        annotationService.add();
        methodService.add();

        context.close();
    }
}
