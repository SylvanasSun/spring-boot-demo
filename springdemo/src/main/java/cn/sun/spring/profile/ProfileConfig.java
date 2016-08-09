package cn.sun.spring.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by sylvanasp on 2016/8/9.
 */
@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev")
    public ProfileBean dev() {
        return new ProfileBean("from dev");
    }

    @Bean
    @Profile("product")
    public ProfileBean product() {
        return new ProfileBean("from product");
    }

}
