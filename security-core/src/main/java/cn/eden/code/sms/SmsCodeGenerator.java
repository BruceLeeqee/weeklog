package cn.eden.code.sms;

import cn.eden.properties.SecurityProperties;
import cn.eden.code.ValidateCode;
import cn.eden.code.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode createCode(ServletWebRequest request) {
        System.out.println("用户自定义的短信验证码生成代码");
        String code= RandomStringUtils.randomNumeric(securityProperties.getCode().getSmsCode().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSmsCode().getExpireIn());
    }
}
