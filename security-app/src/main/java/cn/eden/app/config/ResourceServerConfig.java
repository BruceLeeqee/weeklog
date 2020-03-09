package cn.eden.app.config;

import cn.eden.app.handler.CustomizeAuthenticationSuccessHandler;
import cn.eden.auth.smscode.SmsCodeAuthenticationConfig;
import cn.eden.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 认证服务
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{



    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;

    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder( ){
        DelegatingPasswordEncoder delegatingPasswordEncoder =
                (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());
        return  delegatingPasswordEncoder;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/auth/determine") //需要用户认证的url重定向的页面
                .loginProcessingUrl(securityProperties.getBrowser().getLoginProcessUrl())
                .defaultSuccessUrl(securityProperties.getBrowser().getSuccessPage())
                .successHandler(customizeAuthenticationSuccessHandler)
                .and()
                .authorizeRequests()
                .antMatchers(securityProperties.getBrowser().getLoginpage(),
                        securityProperties.getBrowser().getRegisterpage(),
                        securityProperties.getBrowser().getRegisterProcessUrl(),"/auth/determine","/code/image","/code/smscode","/login/smscode").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .apply(smsCodeAuthenticationConfig);
    }

}
