package cn.sun.sylvanas.spring_boot_security.dao;

import cn.sun.sylvanas.spring_boot_security.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sylvanasp on 2016/8/2.
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long> {

    SysUser findByUsername(String username);

}
