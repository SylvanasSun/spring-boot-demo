package cn.sun.sylvanas.spring_boot_integration;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.mail.Mail;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;

/**
 * 演示读取https://spring.io/blog.atom的新闻聚合文件.
 * 将读取到的消息通过分类(Category),将消息转到不同的消息通道,
 * 将分类为releases和engineering的消息写入磁盘文件,
 * 将分类为news的消息通过邮件发送.
 */
@SpringBootApplication
public class SpringBootIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIntegrationApplication.class, args);
    }

    // 通过@Value注解获得资源
    @Value("https://spring.io/blog.atom")
    private Resource resource;

    /**
     * 使用Fluent API和Pollers配置默认的轮询方式
     */
    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    /**
     * FeedEntryMessageSource实际为feed:inbound-channel-adapter,
     * 此处即构造feed的入站通道适配器作为数据输入.
     */
    @Bean
    public FeedEntryMessageSource feedEntryMessageSource() throws IOException {
        FeedEntryMessageSource feedEntryMessageSource =
                new FeedEntryMessageSource(resource.getURL(), "news");
        return feedEntryMessageSource;
    }

    @Bean
    public IntegrationFlow myFlow() throws IOException {
        return IntegrationFlows.from(feedEntryMessageSource()) // 流程从from方法开始
                /**
                 * 通过路由方法 route 来选择路由,消息体(payload)的类型为SyndEntry,
                 * 作为判断条件的类型为String,判断的值是通过payload获得的分类
                 */
                .<SyndEntry, String>route(payload -> payload.getCategories().get(0).getName(),
                        /**
                         * 通过不同分类的值转向不同的消息通道.
                         * 例: mapping.channelMapping("news","newsChannel")
                         * 若分类为news,则转向newsChannel通道.
                         */
                        mapping -> mapping.channelMapping("releases", "releasesChannel")
                                .channelMapping("engineering", "engineeringChannel")
                                .channelMapping("news", "newsChannel")
                                // 通过get方法获得IntegrationFlow实体,配置为Spring的Bean.
                                .get();
    }

    @Bean
    public IntegrationFlow releasesFlow() {
        return IntegrationFlows.from(MessageChannels.queue("releasesChannel", 10)) // 从通道releasesChannel获得数据
                /**
                 * 使用transform进行数据转换,
                 * payload类型为SyndEntry,将其转换为字符串类型,并自定义数据的格式.
                 */
                .<SyndEntry, String>transform(
                        payload -> "《" + payload.getTitle() + "》 " + payload.getLink()
                                + getProperty("line.separator"))
                /**
                 * 使用handle方法处理file的出站适配器。Files类是由Spring Integration Java DSL提供的
                 * Fluent API 用来构造文件输出的适配器.
                 */
                .handle(Files.outboundAdapter(new File("e:/springblog"))
                        .fileExistsMode(FileExistsMode.APPEND) //4
                        .charset("UTF-8") //5
                        .fileNameGenerator(message -> "releases.txt") //6
                        .get())
                .get();
    }

    @Bean
    public IntegrationFlow engineeringFlow() {
        return IntegrationFlows.from(MessageChannels.queue("engineeringChannel", 10))
                .<SyndEntry, String>transform(
                        payload -> "《" + payload.getTitle() + "》 " + payload.getLink()
                                + getProperty("line.separator"))
                .handle(Files.outboundAdapter(new File("e:/springblog"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .charset("UTF-8")
                        .fileNameGenerator(message -> "engineering.txt")
                        .get())
                .get();
    }

    @Bean
    public IntegrationFlow newsFlow() {
        return IntegrationFlows.from(MessageChannels.queue("newsChannel", 10))
                .<SyndEntry, String>transform(
                        payload -> "《" + payload.getTitle() + "》 "
                                + payload.getLink() + getProperty("line.separator"))
                /**
                 * 通过enrichHeaders来增加信息头的消息
                 * 邮件发送的相关信息通过Spring Integration Java DSL提供的Mail的headers方法构造
                 */
                .enrichHeaders(
                        Mail.headers()
                                .subject("来自Spring的新闻")
                                .to("wisely-man@126.com")
                                .from("wisely-man@126.com"))
                /**
                 * 使用handle方法来定义邮件发送的出站适配器,
                 * 使用Spring Integration Java DSL提供的Mail.outboundAdapter来构造.
                 */
                .handle(Mail.outboundAdapter("smtp.126.com")
                        .port(25)
                        .protocol("smtp")
                        .credentials("wisely-man@126.com", "******")
                        .javaMailProperties(p -> p.put("mail.debug", "false")), e -> e.id("smtpOut"))
                .get();
    }

}
