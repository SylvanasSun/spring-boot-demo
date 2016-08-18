package cn.sun.sylvanas.spring_boot_batch.domain;

import javax.validation.constraints.Size;

/**
 * Created by sylvanasp on 2016/8/2.
 */
public class Person {

    @Size(max = 4,min = 2) // 使用JSR-303注解校验数据
    private String name;

    private Integer age;

    private String nation;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
