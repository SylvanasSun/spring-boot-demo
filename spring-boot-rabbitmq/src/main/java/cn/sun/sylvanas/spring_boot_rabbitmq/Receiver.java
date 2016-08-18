package cn.sun.sylvanas.spring_boot_rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收者
 * Created by sylvanasp on 2016/8/3.
 */
@Component
public class Receiver {

    @RabbitListener(queues = "my-queue")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }

}
