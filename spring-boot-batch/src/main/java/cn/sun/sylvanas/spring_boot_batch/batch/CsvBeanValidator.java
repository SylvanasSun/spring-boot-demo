package cn.sun.sylvanas.spring_boot_batch.batch;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 自定义的数据校验器
 */
public class CsvBeanValidator<T> implements Validator<T>, InitializingBean {

    // 声明JSR-303的Validator来进行检验数据.
    private javax.validation.Validator validator;

    /**
     * 初始化JSR-303的Validator
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    /**
     * 使用Validator的validate方法检验数据
     */
    @Override
    public void validate(T value) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(value);
        // 如果检验出错,拼接错误消息,并抛出数据校验异常.
        if (constraintViolations.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                message.append(constraintViolation.getMessage() + "\n");
            }
            throw new ValidationException(message.toString());
        }
    }
}
