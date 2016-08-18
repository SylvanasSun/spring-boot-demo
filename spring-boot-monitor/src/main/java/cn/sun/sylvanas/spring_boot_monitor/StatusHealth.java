package cn.sun.sylvanas.spring_boot_monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Status的健康指示器
 * Created by sylvanasp on 2016/8/4.
 */
@Component
public class StatusHealth implements HealthIndicator {

    @Autowired
    StatusService statusService;

    @Override
    public Health health() {
        String status = statusService.getStatus();
        // 当status为null 或 非running时构造失败.
        if (status == null || !status.equals("running")) {
            return Health.down().withDetail("Error", "Not Running").build();
        }
        return Health.up().build();
    }
}
