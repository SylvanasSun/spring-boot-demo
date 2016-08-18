package cn.sun.springmvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by sylvanasp on 2016/7/23.
 */
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        // 注册配置类
        webApplicationContext.register(MyMVCConfig.class);
        // 关联servletContext
        webApplicationContext.setServletContext(servletContext);
        // 注册Spring MVC的 DispatcherServlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
                new DispatcherServlet(webApplicationContext));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
        dispatcher.setAsyncSupported(true);// 开启异步支持
    }

}
