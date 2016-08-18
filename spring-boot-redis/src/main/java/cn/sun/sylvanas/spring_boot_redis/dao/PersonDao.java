package cn.sun.sylvanas.spring_boot_redis.dao;

import cn.sun.sylvanas.spring_boot_redis.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by sylvanasp on 2016/8/1.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Repository
public class PersonDao {

    /*
     * Spring Boot已自动配置StringRedisTemplate,所以可以直接注入.
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*
     * Spring Boot已自动配置RedisTemplate,所以可以直接注入.
     */
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /*
     * 可以使用@Resource注解指定stringRedisTemplate,
     * 注入基于字符串的简单属性操作方法.
     */
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valueOperationsStr;

    /*
     * 可以使用@Resource注解指定redisTemplate,
     * 注入基于对象的简单属性操作方法.
     */
    @Resource(name = "redisTemplate")
    private ValueOperations<Object, Object> valueOperations;

    /**
     * 通过set方法,存储字符串类型.
     */
    public void stringRedisTemplateDemo() {
        valueOperationsStr.set("xx", "yy");
    }

    /**
     * 通过set方法,存储对象类型.
     */
    public void save(Person person) {
        valueOperations.set(person.getId(), person);
    }

    /**
     * 通过get方法,获得字符串.
     */
    public String getString() {
        return valueOperationsStr.get("xx");
    }

    /**
     * 通过get方法,获得id为1(key值)的Person对象.
     */
    public Person getPerson() {
        return (Person) valueOperations.get("1");
    }

}
