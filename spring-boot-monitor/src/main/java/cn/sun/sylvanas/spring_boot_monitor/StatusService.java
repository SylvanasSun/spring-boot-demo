package cn.sun.sylvanas.spring_boot_monitor;

import org.springframework.stereotype.Service;

/**
 * 状态服务,仅为改变status的值
 * Created by sylvanasp on 2016/8/4.
 */
@Service
public class StatusService {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
