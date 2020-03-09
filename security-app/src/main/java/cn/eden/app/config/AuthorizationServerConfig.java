package cn.eden.app.config;

import cn.eden.app.MyRdisTokenStore;
import cn.eden.properties.Oauth2ClientProperties;
import cn.eden.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 授权服务
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    //默认token，将用户信息保存在redis
//    @Bean
//    public MyRdisTokenStore tokenStore() {
//        return new MyRdisTokenStore(redisConnectionFactory);
//    }

    //通过自包含的JWT保存用户信息
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter=new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("签名");
        return  jwtAccessTokenConverter;
    }

    @Autowired
    SecurityProperties securityProperties;
    /**
     * 配置认证组件
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .tokenStore(this.tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        if(securityProperties.getOauth2().getClients()!=null){
            int le=securityProperties.getOauth2().getClients().length;
            for (Oauth2ClientProperties oauth2ClientProperties:securityProperties.getOauth2().getClients()){
                clients.inMemory().withClient(oauth2ClientProperties.getClientId()).secret(oauth2ClientProperties.getClientSecret()) //系统配置不起作用了
                        .accessTokenValiditySeconds(oauth2ClientProperties.getAccessTokenValidatySeconds())
                        .authorizedGrantTypes("refresh_token","password","authorization_code")
                        .scopes("all","read","write");
//                        .and()
//                        .withClient("xxx");
            }
        }
    }

}
