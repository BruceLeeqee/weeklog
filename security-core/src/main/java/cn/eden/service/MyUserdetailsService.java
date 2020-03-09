package cn.eden.service;

import cn.eden.repository.entity.User;
import cn.eden.repository.hibernateRepo.UserRepository;
import cn.eden.repository.mybatisRepo.UserMybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *  实现登陆功能
 *  @author 李琦
 */
@Component("userDetailsService")
public class MyUserdetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    private static UserRepository userRepository;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        userRepository = this.repository;
    }

    /**
     * 根据手机号从数据库查询用户信息
     * 和前端输入的密码，进行加密后，进行对比
     * @param phone
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user=userRepository.findByPhone(phone);
        return user;
    }

//    @Override
//    public SocialUserDetails loadUserByUserId(String phone) throws UsernameNotFoundException {
//        User user=userRepository.findByPhone(phone);
//        return user;
//    }
}
