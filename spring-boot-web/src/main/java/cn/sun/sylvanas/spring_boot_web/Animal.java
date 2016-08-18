package cn.sun.sylvanas.spring_boot_web;

/**
 * Created by sylvanasp on 2016/7/28.
 */
public class Animal {
    private String name;
    private Integer age;

    public Animal() {

    }

    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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
}
