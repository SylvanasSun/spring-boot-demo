package cn.sun.sylvanas.spring_boot_jpa.service;

import cn.sun.sylvanas.spring_boot_jpa.domain.Person;

/**
 * Created by sylvanasp on 2016/8/1.
 */
public interface DemoService {

    public Person savePersonWithRollBack(Person person);

    public Person savePersonWithoutRollBack(Person person);

    public Person save(Person person);

    public void remove(Long id);

    public Person findOne(Person person);

}
