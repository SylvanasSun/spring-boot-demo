package cn.sun.sylvanas.spring_boot_web.dao;

import cn.sun.sylvanas.spring_boot_web.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sylvanasp on 2016/8/3.
 */
public interface UserRepository extends JpaRepository<SysUser, Long> {

    public SysUser findByUsername(String username);

}
