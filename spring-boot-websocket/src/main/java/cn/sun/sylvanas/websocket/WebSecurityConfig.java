package cn.sun.sylvanas.websocket;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by sylvanasp on 2016/7/27.
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login").permitAll() // 设置Spring Security 对"/"和"/login"路径不拦截
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") // 设置Spring Security的登陆页面访问路径为/login
                .defaultSuccessUrl("/chat") // 设置登录成功后的跳转路径
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * 在内存中分别配置两个用户 sun 和 sylvanas 密码和用户一致,角色是USER
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("sun").password("sun").roles("USER")
                .and()
                .withUser("sylvanas").password("sylvanas").roles("USER");
    }

    /**
     * /resources/static/目录下的静态资源,Spring Security不拦截.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }
}
