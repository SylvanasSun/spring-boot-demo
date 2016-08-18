package cn.sun.sylvanas.spring_boot_security.domain;

/**
 * 用来测试不同角色用户的数据展示
 * Created by sylvanasp on 2016/8/2.
 */
public class Msg {
    private String title;
    private String content;
    private String etraInfo;

    public Msg(String title, String content, String etraInfo) {
        this.title = title;
        this.content = content;
        this.etraInfo = etraInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEtraInfo() {
        return etraInfo;
    }

    public void setEtraInfo(String etraInfo) {
        this.etraInfo = etraInfo;
    }
}
