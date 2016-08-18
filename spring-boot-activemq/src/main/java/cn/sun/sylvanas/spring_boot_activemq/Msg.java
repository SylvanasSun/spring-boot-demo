package cn.sun.sylvanas.spring_boot_activemq;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 消息发送者
 * Created by sylvanasp on 2016/8/3.
 */
public class Msg implements MessageCreator {

    /**
     * 重写createMessage方法.
     */
    @Override
    public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage("Hello World");
    }

}
