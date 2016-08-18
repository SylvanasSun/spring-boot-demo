package cn.sun.sylvanas.spring_boot_batch.batch;

import cn.sun.sylvanas.spring_boot_batch.domain.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * 自定义的处理类
 * 继承ValidatingItemProcessor类支持校验.
 */
public class CsvItemProcessor extends ValidatingItemProcessor<Person> {

    @Override
    public Person process(Person item) throws ValidationException {
        // 需执行super.process(item) 才会调用自定义的校验器
        super.process(item);
        // 对数据进行简单处理,若民族为汉族则转换成01,其他为02.
        if (item.getNation().equals("汉族")) {
            item.setNation("01");
        } else {
            item.setNation("02");
        }
        return item;
    }
}
