package cn.sun.sylvanas.spring_boot_rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("SpringJavaAutowiringInspection")
@SpringBootApplication
public class SpringBootRabbitmqApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitmqApplication.class, args);
	}

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 定义目的地
     */
	@Bean
	public Queue myQueue() {
		return new Queue("my-queue");
	}


	/**
	 * 使用rabbitTemplate的convertAndSend方法向队列my-queue发送消息.
     */
	@Override
	public void run(String... strings) throws Exception {
		rabbitTemplate.convertAndSend("my-queue","Hello RabbitMQ!");
	}
}
