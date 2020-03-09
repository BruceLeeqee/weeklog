package cn.eden.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @author 李琦
 * 请求配置项——>自定义配置项——>默认配置项
 */
@ConfigurationProperties(prefix = "cn.eden.security")
public class SecurityProperties {
    /**
     * browser里的配置 都会读取到BrowserProperties对象中去
     */
    private  BrowserProperties browser=new BrowserProperties();
    /**
     * ImageCode里得配置读取到ValidateCodeProperties对象中去
     * @return
     */
    private ValidateCodeProperties code=new ValidateCodeProperties();
    /**
     * Oauth2相关配置
     */
    private Oauth2Properties oauth2=new Oauth2Properties();

    public Oauth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(Oauth2Properties oauth2) {
        this.oauth2 = oauth2;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
