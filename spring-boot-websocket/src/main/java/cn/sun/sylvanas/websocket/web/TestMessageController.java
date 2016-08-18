package cn.sun.sylvanas.websocket.web;

import cn.sun.sylvanas.websocket.domain.TestMessage;
import cn.sun.sylvanas.websocket.domain.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by sylvanasp on 2016/7/27.
 */
@Controller
public class TestMessageController {

    /**
     * 通过SimpMessagingTemplate向浏览器发送消息.
     */
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Spring MVC可以直接在参数中注入principal对象,它包含当前用户的信息.
     */
    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        /**
         * 通过SimpMessagingTemplate.convertAndSendToUser向用户发送消息,
         * 第一个参数是接收者的用户,第二参数是浏览器订阅的地址,第三个参数是要发送的消息.
         */
        if (principal.getName().equals("sun")) {
            messagingTemplate.convertAndSendToUser("sylvanas",
                    "/queue/notifications", principal.getName() + "-send:" + msg);
        } else {
            messagingTemplate.convertAndSendToUser("sun",
                    "/queue/notifications", principal.getName() + "-send:" + msg);
        }
    }


    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public TestResponse say(TestMessage message) throws Exception {
        Thread.sleep(3000);
        return new TestResponse("Welcome, " + message.getName() + "!");
    }

    /**
     * @MessageMapping注解映射/hello这个地址,类似于@RequestMapping
     * 当服务端有消息时,会对订阅了@SendTo中的路径的浏览器发送消息.
     */
    @MessageMapping("/hello")
    @SendTo("/topic/getHello")
    public String hello(String message) throws Exception {
        Thread.sleep(3000);
        return "Hello " + message + "!";
    }

}
