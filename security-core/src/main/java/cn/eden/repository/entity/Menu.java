package cn.eden.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = -4052880560031590527L;
    private String name;
    private String url;
    private String path;
    private String component;
    private String iconCls;
    private String keepAlive;
    private String requireAuth;
    private String parentId;
    @ManyToMany(cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
    @JoinTable(name="menu_role",joinColumns = {@JoinColumn(name="rid")},inverseJoinColumns = {@JoinColumn(name="mid")})
    private List<Role> roles=new ArrayList<Role>();


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(String keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(String requireAuth) {
        this.requireAuth = requireAuth;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
