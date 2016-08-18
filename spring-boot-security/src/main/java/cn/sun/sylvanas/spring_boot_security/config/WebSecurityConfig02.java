package cn.sun.sylvanas.spring_boot_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by sylvanasp on 2016/8/7.
 */
public class WebSecurityConfig02 extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /**
     * JDBC认证用户.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,true"
                        + "from t_user where username = ?")
                .authoritiesByUsernameQuery("select username,role"
                        + "from t_role where username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() // 所有请求需要认证登陆后才能访问.
                .and()
                .formLogin() // 通过formLogin定制登录操作.
                    .loginPage("/login") // 定制登录页面的访问地址.
                    .defaultSuccessUrl("/index") // 登录成功后转向的页面.
                    .failureUrl("/login?error") // 登录失败后转向的页面.
                    .permitAll()
                .and()
                .rememberMe() // 开启cookie存储用户信息.
                    .tokenValiditySeconds(1209600) // 指定cookie的有效时间,单位为秒.
                    .key("userKey") // 指定cookie中的私钥.
                .and()
                .logout() // 通过logout()定制注销操作.
                    .logoutUrl("/logout") // 指定注销的URL路径.
                    .logoutSuccessUrl("/index") // 指定注销成功后转向的页面.
                .permitAll();
    }

    /**
     * 放行/resources/static/目录下的静态资源
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }
}
