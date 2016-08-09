package cn.sun.spring.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * Created by sylvanasp on 2016/7/22.
 */
@Service
public class AwareService implements BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    public void outputResult() {
        System.out.println("Bean Name : " + beanName);
    }
}
