package cn.sun.spring.condition;

/**
 * Created by sylvanasp on 2016/7/23.
 */
public class WindowsBean implements ShowListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
