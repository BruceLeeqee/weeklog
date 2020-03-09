package cn.eden.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="role")
public class Role extends BaseEntity  {


    private static final long serialVersionUID = 1113970239196363506L;
    private String name;
    private String nameZh;
    /**
     * 被维护方配置mappedBy属性：只能由 维护方 来维护关联属性，否则谁都不会胡
     * mappedBy指向的是 维护方的关联属性
     */
    @ManyToMany(mappedBy = "roles", cascade={CascadeType.PERSIST} , fetch = FetchType.LAZY)
    private List<User> user=new ArrayList<User>();

    @ManyToMany(mappedBy = "roles", cascade={CascadeType.PERSIST} , fetch = FetchType.LAZY)
    private List<Menu> menu=new ArrayList<Menu>();



    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }
}
