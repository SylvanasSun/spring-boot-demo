package cn.sun.sylvanas.spring_boot_mongo.dao;

import cn.sun.sylvanas.spring_boot_mongo.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by sylvanasp on 2016/8/1.
 */
public interface PersonRepository extends MongoRepository<Person, String> {

    /**
     * 支持方法名查询
     */
    Person findByName(String name);

    /**
     * 支持@Query查询,查询参数构造JSON字符串即可.
     */
    @Query("{'age':?0}")
    List<Person> withQueryFindByAge(Integer age);

}
