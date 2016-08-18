package cn.sun.sylvanas.spring_boot_jpa.support;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 继承SimpleJpaRepository可以让我们使用其提供的一些方法.
 */
public class MyRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements MyRepository<T, ID> {

    private final EntityManager entityManager;

    /**
     * 构造函数,需要当前处理的领域类类型和entityManager作为构造参数.
     * 并给entityManager赋值.
     */
    public MyRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public void hello() {
        System.out.println("Hello World");
    }
}
