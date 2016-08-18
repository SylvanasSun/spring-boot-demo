package cn.sun.sylvanas.spring_boot_jpa.specs;

import cn.sun.sylvanas.spring_boot_jpa.domain.Person;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by sylvanasp on 2016/8/6.
 */
public class MySpecification {
    /**
     * 条件为Person的name等于sun.
     */
    public static Specification<Person> personIsSun() {
        return new Specification<Person>() {
            /**
             * Root可以获得需要查询的属性.
             * CriteriaBuilder可以构造查询条件.
             * Root、CriteriaQuery、CriteriaBuilder、Predicate都是JPA的接口.
             */
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), "sun");
            }
        };
    }

}
