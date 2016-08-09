package cn.sun.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by sylvanasp on 2016/7/22.
 */
@Configuration
@ComponentScan("cn.sun.spring.aop")
@EnableAspectJAutoProxy
public class AopConfig {

}
