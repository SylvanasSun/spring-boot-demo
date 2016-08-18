package cn.sun.sylvanas.spring_boot_jpa.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;

/**
 * 自动模糊查询Specification,当值为String类型时会自动like查询,
 * 如果是其他类型则使用equal查询,
 * 如果没有值则查询全部.
 * Created by sylvanasp on 2016/7/31.
 */
public class CustomerSpecs {

    /**
     * 定义一个返回值为Specification的函数byAuto
     * 这里使用的是泛型 T,所以这个Specification是可以用于任意的实体类的.
     * 它接受的参数是entityManager和当前的包含值作为查询条件的实体类对象.
     */
    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) {
        // 获得当前实体类对象的类型
        final Class<T> type = (Class<T>) example.getClass();

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery
                    , CriteriaBuilder criteriaBuilder) {
                // 初始化一个Predicate集合存储构造的查询条件
                List<Predicate> predicateList = new ArrayList<>();
                // 获得实体类的EntityType,可以从EntityType获得实体类的Attribute
                EntityType<T> entity = entityManager.getMetamodel().entity(type);

                /**
                 * 对实体类的所有属性做循环
                 */
                for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
                    // 获得实体类对象某一个属性的值
                    Object attrValue = getValue(example, attr);
                    if (attrValue != null) {
                        // 当前属性值为字符类型的时候
                        if (attr.getJavaType() == String.class) {
                            // 当前字符不为空的情况下
                            if (!StringUtils.isEmpty(attrValue)) {
                                /**
                                 * 构造当前属性like(前后%)属性值查询条件,并添加到条件集合中.
                                 */
                                predicateList.add(criteriaBuilder.like(root.get(attribute(entity, attr.getName(),
                                        String.class)), pattern((String) attrValue)));
                            }
                        } else {
                            // 其他情况下,构造属性和属性值equal查询条件,并添加到条件集合中.
                            predicateList.add(criteriaBuilder.equal(root.get(attribute(entity, attr.getName()
                                    , attrValue.getClass())), attrValue));
                        }
                    }
                }
                // 将条件集合转换成Predicate
                return predicateList.isEmpty() ? criteriaBuilder.conjunction() :
                        criteriaBuilder.and(toArray(predicateList, Predicate.class));
            }

            /**
             * 通过反射获得实体类对象对应属性的属性值
             */
            private <T> Object getValue(T example, Attribute<T, ?> attr) {
                return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
            }

            /**
             * 获得实体类的当前属性的SingularAttribute,
             * SingularAttribute包含的是实体类的某个单独属性
             */
            private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
                                                             Class<E> fieldClass) {
                return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
            }
        };
    }

    /**
     * like查询模式,前后+%
     */
    static private String pattern(String str) {
        return "%" + str + "%";
    }

}
