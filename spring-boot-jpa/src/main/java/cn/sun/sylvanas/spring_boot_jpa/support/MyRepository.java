package cn.sun.sylvanas.spring_boot_jpa.support;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * NoRepositoryBean注解指明当前这个接口不是领域类的Repository接口.
 */
@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends
        PagingAndSortingRepository<T, ID> {

    public void hello();

}
