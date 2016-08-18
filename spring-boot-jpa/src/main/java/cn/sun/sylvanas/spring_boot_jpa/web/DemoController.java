package cn.sun.sylvanas.spring_boot_jpa.web;

import cn.sun.sylvanas.spring_boot_jpa.domain.Person;
import cn.sun.sylvanas.spring_boot_jpa.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sylvanasp on 2016/8/1.
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/rollback")
    public Person rollback(Person person) {
        return demoService.savePersonWithRollBack(person);
    }

    @RequestMapping("/norollback")
    public Person noRollback(Person person) {
        return demoService.savePersonWithoutRollBack(person);
    }

    @RequestMapping("/cacheput")
    public Person put(Person person){
        return demoService.save(person);
    }

    @RequestMapping("/cacheable")
    public Person cacheable(Person person){
        return demoService.findOne(person);
    }

    @RequestMapping("/cacheEvict")
    public String cacheEvict(Long id){
        demoService.remove(id);
        return "ok";
    }

}
