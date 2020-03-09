package cn.eden.code.sms;

import cn.eden.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultSmsSender implements SmsSender {
    private static Logger logger= LoggerFactory.getLogger(DefaultSmsSender.class);

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean send(String mobile, String code) {
        logger.info("向手机号"+mobile+"发送验证码"+code);
        return true;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
