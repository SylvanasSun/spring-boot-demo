package cn.sun.sylvanas.spring_boot_jpa.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 自定义RepositoryFactoryBean需要继承JpaRepositoryFactoryBean.
 * RepositoryFactory将会注册我们自定义的Repository实现.
 */
public class MyRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {

    /**
     * 重写createRepositoryFactory方法,创建我们自定义的RepositoryFactory.
     */
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new MyRepositoryFactory(entityManager);
    }

    /**
     * 自定义的RepositoryFactory继承JpaRepositoryFactory
     */
    private static class MyRepositoryFactory extends JpaRepositoryFactory {

        public MyRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        /**
         * 重写getTargetRepository方法,获得当前自定义的Repository实现.
         */
        @Override
        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?>
            getTargetRepository(RepositoryInformation information, EntityManager entityManager) {

            return new MyRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
        }

        /**
         * 重写getRepositoryBaseClass方法,获得当前自定义的Repository实现的类型.
         */
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return MyRepositoryImpl.class;
        }
    }
}
