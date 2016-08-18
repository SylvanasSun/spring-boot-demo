package cn.sun.sylvanas.spring_boot_starter_hello;

/**
 * Created by sylvanasp on 2016/7/26.
 */
public class HelloService {

    private String msg;

    public String sayHello() {
        return "Hello" + msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
