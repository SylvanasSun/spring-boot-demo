package cn.sun.sylvanas.spring_boot_test.web;

import cn.sun.sylvanas.spring_boot_test.dao.PersonRepositroy;
import cn.sun.sylvanas.spring_boot_test.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sylvanasp on 2016/8/3.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonRepositroy personRepositroy;

    @RequestMapping(method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Person> findAll() {
        return personRepositroy.findAll();
    }

}
