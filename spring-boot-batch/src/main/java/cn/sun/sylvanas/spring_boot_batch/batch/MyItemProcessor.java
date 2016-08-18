package cn.sun.sylvanas.spring_boot_batch.batch;

import cn.sun.sylvanas.spring_boot_batch.domain.Person;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by sylvanasp on 2016/8/9.
 */
public class MyItemProcessor implements ItemProcessor<Person,Person> {

    @Override
    public Person process(Person person) throws Exception {
        String name = person.getName().toUpperCase();
        person.setName(name);
        return person;
    }

}
