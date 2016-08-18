package cn.sun.sylvanas.spring_boot_jpa.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 自定义Repository接口
 * 继承了JpaRepository和JpaSpecificationExecutor,
 * 让我们可以使用JpaRepository所提供和方法和具备Specification的能力.
 * @NoRepositoryBean注解指明这个接口不是领域类的接口.
 * Created by sylvanasp on 2016/7/31.
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {

    Page<T> findByAuto(T example, Pageable pageable);

}
