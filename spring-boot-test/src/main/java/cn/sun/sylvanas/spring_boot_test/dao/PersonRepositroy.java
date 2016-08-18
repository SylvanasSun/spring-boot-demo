package cn.sun.sylvanas.spring_boot_test.dao;

import cn.sun.sylvanas.spring_boot_test.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sylvanasp on 2016/8/3.
 */
public interface PersonRepositroy extends JpaRepository<Person,Long> {

}
