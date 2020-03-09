package cn.eden.repository.hibernateRepo;

import cn.eden.repository.entity.User;
import cn.eden.repository.entity.UserqueryCondition;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * https://blog.csdn.net/lxl_family/article/details/46406923
 * Hibernate会自动将实体类映射到数据库中，为我们建立相关的数据库表，
 * 它会根据@Table、@Column、@Id、@GeneratedValue(strategy = GenerationType.AUTO)等注解实现数据库表的自动匹配。
 * 由于只读事务不存在数据的修改，因此数据库将会为只读事务提供一些优化手段。JPA默认为 非只读事物。需手动指定只读事物@Transactional(readOnly=true)
 * 自定义的插入、更新、删除操作需要加@Modifying注解和@Transactional注解
 *        query作用：将自定义sql绑定到自定义方法中
 *        Modifying作用：更新sq语句绑定到自定义方法，且必须与query注解联合使用【一个注入sql，一个标注为更新语句】
 *
 * JPA: 是一种ORM规范，API
 * Hibernate: 是JPA的一种实现
 * SpringDataJPA: 是一个库，通过规范方法名称，简化业务逻辑代码
 *                通过创建继承Repository的子接口的  代理对象
 *                创建查询方法：解析方法名称，定义命名查询语句，使用@Query
 */
@Component
public interface UserRepository extends JpaRepository<User,Integer> {

    public User save(User user);

    public User findById(Long id); //必须是long类型

    @Query("update User u set u.password=?2 where u.id=?1")
    @Modifying
    @Transactional
    public void update(Long id,String password);

    public User findByPhone(String phone);

}
