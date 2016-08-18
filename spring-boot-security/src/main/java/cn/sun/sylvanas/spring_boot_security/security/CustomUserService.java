package cn.sun.sylvanas.spring_boot_security.security;

import cn.sun.sylvanas.spring_boot_security.dao.SysUserRepository;
import cn.sun.sylvanas.spring_boot_security.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义的用户服务
 * Created by sylvanasp on 2016/8/2.
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;

    /**
     * 重写loadUserByUsername方法获得用户
     * 因为我们当前的SysUser类实现了UserDetails接口,可以直接返回给Spring Security使用.
     * 如果实体类没有实现UserDetails接口,则需要在该方法中封装权限,
     * 并使用org.springframework.security.core.userdetails.User封装返回.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }

        return sysUser;
    }
}
