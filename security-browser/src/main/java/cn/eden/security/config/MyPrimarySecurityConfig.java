//package cn.eden.security.config;
//
//import cn.eden.auth.imgcode.ValidateImageCodeFilter;
//import cn.eden.auth.smscode.ValidateSmsCodeFilter;
//import cn.eden.security.resulthandler.CustomizeAuthenticationFailureHandler;
//import cn.eden.security.resulthandler.CustomizeAuthenticationSuccessHandler;
//import cn.eden.auth.smscode.SmsCodeAuthenticationConfig;
//import cn.eden.properties.SecurityProperties;
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.sql.DataSource;
//
///**
// *  判断需要登陆，才会跳转到登陆页URL
// *  rememberMe登陆流程：UsernamePasswordAuthenticationFilter—>successfulAuthentication()rememberMeServices调用登陆方法
// *                      —>PersistentTokenBasedRememberMeServices创建token存入数据库，保存到Cookie
// *            再次登陆流程：RememberMeAuthenticationFilter若session没有认证对象，则rememberMeServices自动登陆—>从Cookie获取token,
// *                           数据库得到认证对象
// */
//@Configuration
//public class MyPrimarySecurityConfig extends WebSecurityConfigurerAdapter{
//
//    @Autowired
//    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
//
//    @Autowired
//    private SecurityProperties securityProperties;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;
//
//    /**
//     * 配置remember功能：将token存入数据库中
//     * @return
//     */
//    @Bean
//    public PersistentTokenRepository getTokenRepository(){
//        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl(); //自动生成 token和用户得对应关系表
//        jdbcTokenRepository.setDataSource(dataSource);
////        jdbcTokenRepository.setCreateTableOnStartup(true);
//        return jdbcTokenRepository;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        ValidateImageCodeFilter validateCodeFilter=new ValidateImageCodeFilter();
//        validateCodeFilter.setAuthenticationFailureHandler(new CustomizeAuthenticationFailureHandler());
//        validateCodeFilter.setSecurityProperties(securityProperties); //注意配置顺序
//        validateCodeFilter.afterPropertiesSet();
//
//        ValidateSmsCodeFilter smsCodeFilter=new ValidateSmsCodeFilter();
//        smsCodeFilter.setAuthenticationFailureHandler(new CustomizeAuthenticationFailureHandler());
//        smsCodeFilter.setSecurityProperties(securityProperties); //注意配置顺序
//        smsCodeFilter.afterPropertiesSet();
//
//
//
//        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//            .addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
//            .formLogin().loginPage("/auth/determine") //需要用户认证的url重定向的页面
//                        .loginProcessingUrl(securityProperties.getBrowser().getLoginProcessUrl())
//                        .defaultSuccessUrl(securityProperties.getBrowser().getSuccessPage())
//                        .successHandler(customizeAuthenticationSuccessHandler)
//                 .and()
//                 .rememberMe()
//                 .tokenRepository(getTokenRepository())
//                 .tokenValiditySeconds(securityProperties.getBrowser().getRememeberMeSeconds())
//                 .userDetailsService(userDetailsService) //根据用户名查用户信息，放入ContextHolder中进行登陆
//                 .and()
//                 .authorizeRequests()
//                 .antMatchers(securityProperties.getBrowser().getLoginpage(),
//                              securityProperties.getBrowser().getRegisterpage(),
//                              securityProperties.getBrowser().getRegisterProcessUrl(),"/user/me","/auth/determine","/code/image","/code/smscode","/login/smscode").permitAll()
//                 .anyRequest()
//                 .authenticated()
//                 .and()
//                 .csrf().disable()
//                 .apply(smsCodeAuthenticationConfig);
//    }
//}
