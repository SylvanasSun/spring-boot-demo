package cn.sun.sylvanas.spring_boot_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootWebApplication {

    @RequestMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Person search(String personName) {
        return new Person(personName, 19, "tianjin");
    }
    @RequestMapping(value = "/findAnimal",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Animal findAnimal(String animalName) {
        return new Animal(animalName, 3);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}
