package cn.sun.spring.aop;

import java.lang.annotation.*;

/**
 * Created by sylvanasp on 2016/7/22.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
