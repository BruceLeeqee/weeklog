package cn.eden.repository.entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass  //标识实体类的父类，子类都会扫描该基类属性映射到数据库中
public class BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
}
