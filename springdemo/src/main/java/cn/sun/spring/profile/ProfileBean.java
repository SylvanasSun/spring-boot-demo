package cn.sun.spring.profile;

/**
 * Created by sylvanasp on 2016/8/9.
 */
public class ProfileBean {

    private String message;

    public ProfileBean() {

    }

    public ProfileBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
