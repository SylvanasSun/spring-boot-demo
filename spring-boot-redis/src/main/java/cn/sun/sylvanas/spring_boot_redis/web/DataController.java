package cn.sun.sylvanas.spring_boot_redis.web;

import cn.sun.sylvanas.spring_boot_redis.dao.PersonDao;
import cn.sun.sylvanas.spring_boot_redis.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sylvanasp on 2016/8/1.
 */
@RestController
public class DataController {

    @Autowired
    PersonDao personDao;

    @RequestMapping("/set")
    public void set() {
        Person person = new Person("1", "sun", 19);
        personDao.save(person);
        personDao.stringRedisTemplateDemo();
    }

    @RequestMapping("/getStr")
    public String getStr() {
        return personDao.getString();
    }

    @RequestMapping("/getPerson")
    public Person getPerson() {
        return personDao.getPerson();
    }
}
