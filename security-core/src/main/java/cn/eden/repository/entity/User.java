package cn.eden.repository.entity;

import cn.eden.service.MyBCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.*;
import java.util.*;
/**
 * 用于框架登陆得userDetails
 * @ManyToOne  many是本实体一侧
 * cascade（级联关系），fetch（加载策略）,mappedBy（被维护方声明维护放的 关联属性）
 * CascadeType.MERGE级联更新：若items属性修改了那么order对象保存时同时修改items里的对象。对应EntityManager的merge方法 （较常用  ）
 * CascadeType.PERSIST级联保存：对order对象保存时也对items里的对象也会保存。对应EntityManager的presist方法
 * CascadeType.REFRESH级联刷新：获取order对象里也同时也重新获取最新的items时的对象。对应EntityManager的refresh(object)方法有效。即会重新查询数据库里的最新数据
 * CascadeType.REMOVE级联删除：对order对象删除也对items里的对象也会删除。对应EntityManager的remove方法
 */
/**
 * fetch定义数据的加载策略：实现关联数据的选择性加载
 * 懒加载LAZY
 * 实时加载EAGER
 */
/**
 * 在一对多或一对一的关系映射中，如果不表明mappedBy属性，默认是由本方维护外键
 * 如果两方都由本方来维护的话，会多出一些update语句，性能有一定的损耗。
 * 解决的办法就是在一方配置上mappedBy属性
 * 一对多与多对一关系也可能会有中间表关联两者。但是我们一般不建议使用中间表。使用mapperBy可以避免系统生成中间表
 *
 * UserDetails抽象了User对象
 */
/**
 * JoinColumn指定 被维护方的列
 * 被维护方不会主动去维护关联关系。
 * 真正的关系维护，掌握在维护方的手中
 * @return
 */
@Entity
@Table(name="user")
public class User extends BaseEntity implements UserDetails,SocialUserDetails{

    private static final long serialVersionUID = 1742247790621171121L;

    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};
    private String nickname;
    private String phone;   //不能为null
    private String password;//不能为null,调用trim后必须大于0

    @Column(name="head_img")
    private String headImg;
    private String email;
    private String enabled="1";
    private Date createdate;

    public User() {
        this.createdate = new Date();
    }

    @Transient
    private PasswordEncoder passwordEncoder=new MyBCryptPasswordEncoder();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = this.getRoles();
        Set<GrantedAuthority> authorities=new HashSet<>();
        for (int i=0;i<roles.size();i++){
            authorities.add(new SimpleGrantedAuthority(roles.get(i).getName()));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); //为了支持服务提供商 对外服务
        return authorities;
    }
    /**
     * 指定SpringSocial的用户凭证
     * @return
     */
    @Override
    public String getUserId() {
        return phone;
    }
    /**
     * 指定认证的用户凭证
     * @return
     */
    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = {@JoinColumn(name="rid")},inverseJoinColumns = {@JoinColumn(name="uid")})
    private List<Role> roles=new ArrayList<Role>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=passwordEncoder.encode(password);
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = new Date();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
