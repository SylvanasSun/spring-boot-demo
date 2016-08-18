package cn.sun.sylvanas.spring_boot_security.config;

import cn.sun.sylvanas.spring_boot_security.security.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by sylvanasp on 2016/8/2.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 添加自定义的UserDetailService认证.
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() // 所有请求需要认证登陆后才能访问。
                .and()
                .formLogin() // 登陆行为,登陆页面可任意访问
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .permitAll()
                .and()
                .logout().permitAll(); // 注销行为,注销请求可任意访问
    }

}
