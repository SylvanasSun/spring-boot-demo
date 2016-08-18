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
 * Created by sylvanasp on 2016/7/31.
 */
public class CustomRepositoryFactoryBean<T extends JpaRepository<S,ID>,S,ID extends Serializable>
    extends JpaRepositoryFactoryBean<T,S,ID>{

    /**
     * 重写createRepositoryFactory方法,用我们自定义的CustomRepositoryFactory创建实例.
     */
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new CustomRepositoryFactory(entityManager);
    }

    private static class CustomRepositoryFactory extends JpaRepositoryFactory{

        public CustomRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        /**
         * 重写getTargetRepository方法,获得当前自定义的Repository实现.
         */
        @Override
        @SuppressWarnings({"unchecked"})
        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
                RepositoryInformation information, EntityManager entityManager) {
            return new CustomRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
        }

        /**
         * 重写getRepositoryBaseClass方法,获得当前自定义的Repository实现的类型
         */
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return CustomRepositoryImpl.class;
        }
    }
}
