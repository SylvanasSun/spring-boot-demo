package cn.sun.sylvanas.spring_boot_jpa.web;

import cn.sun.sylvanas.spring_boot_jpa.dao.PersonRepository;
import cn.sun.sylvanas.spring_boot_jpa.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.sun.sylvanas.spring_boot_jpa.specs.MySpecification.personIsSun;

/**
 * Created by sylvanasp on 2016/7/31.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
public class DataController {

    @Autowired
    PersonRepository personRepository;

    /**
     * 保存
     * save支持批量保存,<S extends T> Iterable<S> save(Iterable<S> entities);
     * <p/>
     * 删除
     * 支持使用id删除对象,批量删除以及删除全部.
     * void delete(ID id);
     * void delete(T entity);
     * void delete(Iterable<? extends T> entities);
     * void deleteAll();
     */
    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person p = personRepository.save(new Person(name, age, address));
        return p;
    }

    /**
     * 测试findByAddress
     */
    @RequestMapping("/findByAddress")
    public List<Person> findByAddress(String address) {
        List<Person> persons = personRepository.findByAddress(address);
        return persons;
    }

    /**
     * 测试findByNameAndAddress
     */
    @RequestMapping("/findByNameAndAddress")
    public Person findByNameAndAddress(String name, String address) {
        Person person = personRepository.findByNameAndAddress(name, address);
        return person;
    }

    /**
     * 测试withNameAndAddressQuery
     */
    @RequestMapping("/withNameAndAddressQuery")
    public Person withNameAndAddressQuery(String name, String address) {
        Person person = personRepository.withNameAndAddressQuery(name, address);
        return person;
    }

    /**
     * 测试withNameAndAddressNamedQuery
     */
    @RequestMapping("/withNameAndAddressNamedQuery")
    public Person withNameAndAddressNamedQuery(String name, String address) {
        Person person = personRepository.withNameAndAddressNamedQuery(name, address);
        return person;
    }

    /**
     * 测试排序
     * 根据age字段排序.
     */
    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> people = personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
        return people;
    }

    /**
     * 测试分页
     * 返回的Page接口可以获得记录、总页数、总记录数等信息.
     */
    @RequestMapping("/page")
    public Page<Person> page() {
        Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));
        return pagePeople;
    }

    @RequestMapping("/specs")
    public Person specs() {
        return personRepository.findAll(personIsSun()).get(0);
    }
}
