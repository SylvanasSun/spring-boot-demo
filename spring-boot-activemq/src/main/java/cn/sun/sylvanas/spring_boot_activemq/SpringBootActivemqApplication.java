package cn.sun.sylvanas.spring_boot_activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

/**
 * Spring Boot提供了一个CommandLineRunner接口,用于程序启动后执行的代码
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@SpringBootApplication
public class SpringBootActivemqApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootActivemqApplication.class, args);
	}

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void run(String... strings) throws Exception {
		// 向目的地my-destination发送消息
		jmsTemplate.send("my-destination",new Msg());
	}
}
