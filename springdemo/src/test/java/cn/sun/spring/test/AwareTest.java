package cn.sun.spring.test;

import cn.sun.spring.aware.AwareConfig;
import cn.sun.spring.aware.AwareService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by sylvanasp on 2016/7/22.
 */
public class AwareTest {

    @Test
    public void testAware() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareService awareService = context.getBean(AwareService.class);
        awareService.outputResult();
        context.close();
    }
}
