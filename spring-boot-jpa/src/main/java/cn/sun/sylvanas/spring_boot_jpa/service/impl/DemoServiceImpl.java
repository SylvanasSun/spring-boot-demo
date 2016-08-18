package cn.sun.sylvanas.spring_boot_jpa.service.impl;

import cn.sun.sylvanas.spring_boot_jpa.dao.PersonRepository;
import cn.sun.sylvanas.spring_boot_jpa.domain.Person;
import cn.sun.sylvanas.spring_boot_jpa.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sylvanasp on 2016/8/1.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    PersonRepository personRepository;

    /**
     * rollbackFOr指定特定异常数据回滚.
     */
    @Override
    @Transactional(rollbackFor = (IllegalArgumentException.class))
    public Person savePersonWithRollBack(Person person) {
        Person save = personRepository.save(person);
        if (save.getName().equals("汪云飞")) {
            throw new IllegalArgumentException("汪云飞已存在,数据将回滚");
        }
        return save;
    }

    /**
     * noRollbackFor指定特定异常数据不回滚.
     */
    @Override
    @Transactional(noRollbackFor = (IllegalArgumentException.class))
    public Person savePersonWithoutRollBack(Person person) {
        Person save = personRepository.save(person);
        if (save.getName().equals("汪云飞")) {
            throw new IllegalArgumentException("汪云飞已存在,数据将不会回滚");
        }
        return save;
    }

    /**
     * CachePut缓存新增的或更新的数据到缓存,其中缓存名称为people,数据的key是person的id.
     */
    @Override
    @CachePut(value = "people", key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id、key为:" + p.getId() + "数据做了缓存.");
        return p;
    }

    /**
     * CacheEvict从缓存people中删除key为id的数据.
     * 如果没有指定key,则方法参数作为key保存到缓存中.
     */
    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {
        System.out.println("删除了id、key为:" + id + "的数据缓存.");
        personRepository.delete(id);
    }

    /**
     * Cacheable缓存key为person的id数据到缓存people中.
     */
    @Override
    @Cacheable(value = "people", key = "#person.id")
    public Person findOne(Person person) {
        Person one = personRepository.findOne(person.getId());
        System.out.println("为id、key为:" + one.getId() + "数据做了缓存.");
        return one;
    }
}
