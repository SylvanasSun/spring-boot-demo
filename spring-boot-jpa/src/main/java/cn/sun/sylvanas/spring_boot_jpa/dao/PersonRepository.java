package cn.sun.sylvanas.spring_boot_jpa.dao;

import cn.sun.sylvanas.spring_boot_jpa.domain.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Person数据访问接口
 * Created by sylvanasp on 2016/7/31.
 */
@RepositoryRestResource(path = "people")
public interface PersonRepository extends JpaRepository<Person, Long>,JpaSpecificationExecutor<Person> {

    /**
     * 使用方法名查询,接收一个name参数,返回List列表
     *
     * @param name
     * @return
     */
    List<Person> findByAddress(String name);

    /**
     * 使用方法名查询,接收name和address参数,返回单个对象
     *
     * @param name
     * @param address
     * @return
     */
    Person findByNameAndAddress(String name, String address);

    /**
     * 查询符合条件的前20条数据
     *
     * @param name
     * @return
     */
    List<Person> findTop20ByName(String name);

    /**
     * 使用@Query查询,参数按照名称绑定.
     *
     * @param name
     * @param address
     * @return
     */
    @Query("select p from Person p where p.name = :name and p.address = :address")
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    /**
     * 使用@NamedQuery查询,@NamedQuery在实体类Person中已定义.
     *
     * @param name
     * @param address
     * @return
     */
    Person withNameAndAddressNamedQuery(String name, String address);

    @RestResource(path = "nameStartsWith",rel = "nameStartsWith")
    Person findByNameStartsWith(@Param("name") String name);

    /**
     * 更新查询,返回值Int返回的是更新语句影响的行数.
     * @Transactional注解开启事务.
     */
    @Query("update Person p set p.name = :name")
    @Modifying
    @Transactional
    int setName(@Param("name") String name);


}
