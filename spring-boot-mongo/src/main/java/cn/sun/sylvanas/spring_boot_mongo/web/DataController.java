package cn.sun.sylvanas.spring_boot_mongo.web;

import cn.sun.sylvanas.spring_boot_mongo.dao.PersonRepository;
import cn.sun.sylvanas.spring_boot_mongo.domain.Location;
import cn.sun.sylvanas.spring_boot_mongo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by sylvanasp on 2016/8/1.
 */
@RestController
public class DataController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save() {
        Person person = new Person("sun", 19);
        Collection<Location> locations = new LinkedHashSet<Location>();
        Location l1 = new Location("天津", "2016");
        Location l2 = new Location("北京", "2018");
        Location l3 = new Location("上海", "2020");
        locations.add(l1);
        locations.add(l2);
        locations.add(l3);
        person.setLocations(locations);
        return personRepository.save(person);
    }

    @RequestMapping("/q1")
    public Person q1(String name) {
        return personRepository.findByName(name);
    }

    @RequestMapping("/q2")
    public List<Person> q2(Integer age) {
        return personRepository.withQueryFindByAge(age);
    }

}
