package cn.sun.sylvanas.spring_boot_activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息的接收者
 * Created by sylvanasp on 2016/8/3.
 */
@Component
public class Receiver {

    /**
     * @JmsListener是Spring 4.1提供的一个新特性,用来简化JMS开发.
     * destination指定要监听的目的地.
     */
    @JmsListener(destination = "my-destination")
    public void receiveMessage(String message) {
        System.out.println("接收到: <" + message + ">");
    }

}
