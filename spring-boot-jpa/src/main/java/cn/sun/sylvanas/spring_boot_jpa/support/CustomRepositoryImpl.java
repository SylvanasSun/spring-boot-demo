package cn.sun.sylvanas.spring_boot_jpa.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static cn.sun.sylvanas.spring_boot_jpa.specs.CustomerSpecs.byAuto;

/**
 * 继承JpaRepository的实现类SimpleJpaRepository,实现了CustomRepository接口.
 * Created by sylvanasp on 2016/7/31.
 */
public class CustomRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    /**
     * 使用byAuto Specification构造的条件查询,并提供分页功能.
     */
    @Override
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(byAuto(entityManager, example), pageable);
    }
}
