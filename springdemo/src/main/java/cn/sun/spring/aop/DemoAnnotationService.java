package cn.sun.spring.aop;

import org.springframework.stereotype.Service;

/**
 * Created by sylvanasp on 2016/7/22.
 */
@Service
public class DemoAnnotationService {

    @Action(name="注解式拦截")
    public void add(){};

}
