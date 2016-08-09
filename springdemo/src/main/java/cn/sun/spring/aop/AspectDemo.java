package cn.sun.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by sylvanasp on 2016/7/22.
 */
@Aspect
@Component
public class AspectDemo {

    @Pointcut("@annotation(cn.sun.spring.aop.Action)")
    private void annotationPointCut() {
    }



    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        // 获得签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 通过签名获得方法
        Method method = signature.getMethod();
        // 通过方法获得方面上的注解
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截: " + action.name());
    }

    @Before("execution(* cn.sun.spring.aop.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint) {
        // 获得签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 通过签名获得方法
        Method method = signature.getMethod();
        System.out.println("方法规则拦截: " + method.getName());
    }

}
