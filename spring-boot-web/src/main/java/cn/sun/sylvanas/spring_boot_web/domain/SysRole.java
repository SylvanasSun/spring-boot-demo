package cn.sun.sylvanas.spring_boot_web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sylvanasp on 2016/8/3.
 */
@Entity
public class SysRole {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public SysRole() {
        super();
    }

    public SysRole(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
