package cn.eden.repository.mybatisRepo;

import cn.eden.repository.entity.User;

/**
 * 暴漏对外的方法
 * 在抽象类中，隐藏实现的方法
 */
public interface UserMybatisDao{
    public User findByUsername(String name);
}
