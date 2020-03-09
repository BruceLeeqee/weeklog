package cn.eden.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * SpringSecurity默认配置    优先级低于 ResourceServerConfigurerAdapter
 * 访问权限配置(URL的认证)
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    protected UserDetailsService userDetailsService() {
        return new MyUserdetailsService();
    }


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置认证管理器
     * 不进行注入，可以统一进行配置
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new AdminAuthenticationProvide(userDetailsService()));
    }

    /**
     * 配置首次登陆的认证处理器（默认使用BCryptPasswordEncoder进行匹配）
     * @return
     */
//    @Bean("adminAuthenticationProvider")
//    public AdminAuthenticationProvider adminAuthenticationProvider() {
//        AdminAuthenticationProvider daoProvider = new AdminAuthenticationProvider();
//        daoProvider.setUserDetailsService(userDetailsService()); // 将自定义的认证逻辑添加到DaoAuthenticationProvider
//        daoProvider.setPasswordEncoder(passwordEncoder()); // 设置自定义的密码加密
//        return daoProvider;
//    }
}