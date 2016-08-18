package cn.sun.springmvc.config;

import cn.sun.springmvc.converter.MyMessageConverter;
import cn.sun.springmvc.interceptor.HandlngTimeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Created by sylvanasp on 2016/7/23.
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("cn.sun.springmvc")
public class MyMVCConfig extends WebMvcConfigurerAdapter {

    /**
     * 视图解析器
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 添加静态资源访问路径
     * addResourceHandler指的是对外暴露的访问路径
     * addResourceLocations指的是文件放置的目录
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }

    /**
     * 注册拦截器
     */
    @Bean
    public HandlngTimeInterceptor handlngTimeInterceptor(){
        return new HandlngTimeInterceptor();
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlngTimeInterceptor());
    }

    /**
     * 多媒体解析器(文件上传)
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    /**
     * 页面跳转控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/async").setViewName("async");
    }

    /**
     * 路径匹配参数设置
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 设置不忽略"."后面的参数
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 自定义HttpMessageConverter
     */
    @Bean
    public MyMessageConverter myMessageConverter(){
        return new MyMessageConverter();
    }

    /**
     * 注册自定义的HttpMessageConverter
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(myMessageConverter());
    }
}
