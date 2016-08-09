package cn.sun.spring.test;

import cn.sun.spring.event.DemoPublisher;
import cn.sun.spring.event.EventConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by sylvanasp on 2016/7/22.
 */
public class EventTest {

    @Test
    public void testEvent() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);

        DemoPublisher publisher = context.getBean(DemoPublisher.class);

        publisher.publish("hello application event!!");

        context.close();
    }

}
