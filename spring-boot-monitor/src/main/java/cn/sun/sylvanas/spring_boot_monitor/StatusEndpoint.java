package cn.sun.sylvanas.spring_boot_monitor;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 自定义的端点.
 * 继承AbstractEndpoint类,重写Invoke方法返回我们要监控的内容.
 * 实现ApplicationContextAware接口可以让我们访问容器的资源.
 * Created by sylvanasp on 2016/8/4.
 */
@ConfigurationProperties(prefix = "endpoints.status",ignoreInvalidFields = false)
public class StatusEndpoint extends AbstractEndpoint<String> implements ApplicationContextAware {

    ApplicationContext context;

    public StatusEndpoint() {
        super("status");
    }


    @Override
    public String invoke() {
        StatusService statusService = context.getBean(StatusService.class);
        return "The Current Status is :" + statusService.getStatus();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
