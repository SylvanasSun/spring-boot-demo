package cn.sun.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by sylvanasp on 2016/7/22.
 */
public class DemoEvent extends ApplicationEvent {

    private String msg;


    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
