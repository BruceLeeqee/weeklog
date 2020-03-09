package cn.eden.code;

import cn.eden.properties.SecurityProperties;
import cn.eden.code.sms.DefaultSmsSender;
import cn.eden.code.sms.SmsSender;
import cn.eden.code.image.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李琦
 * 配置接口的实现类
 * 配置类进行统一配置，省去在代码中使用注解进行配置
 * 可配置不同的代码生成器，短信发送者
 *
 */
@Configuration
public class ValidateCodeGeneratorConfig {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 动态配置验证码生成器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name="imageCodeGenerator") //若系统存在此名称的bean,则不用此处的默认配置了
    ValidateCodeGenerator generateGenerator(){ //配置接口的实现类
        ImageCodeGenerator imageCodeGenerator= new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return  imageCodeGenerator;
    }
    /**
     * 动态配置短信发送器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsSender.class)
    SmsSender generateSmsSender(){
        DefaultSmsSender smsSender=new DefaultSmsSender();
        smsSender.setSecurityProperties(securityProperties);
        return smsSender;
    }
}
