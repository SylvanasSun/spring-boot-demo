package cn.sun.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by sylvanasp on 2016/7/26.
 */
@Configuration
@EnableWebMvc
@ComponentScan("cn.sun.spring")
public class ThymeleafMVCConfig extends WebMvcConfigurerAdapter {



}
