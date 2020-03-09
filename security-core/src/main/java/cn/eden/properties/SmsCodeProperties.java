package cn.eden.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class SmsCodeProperties {
    private int length = 6;   //验证码长度
    private int expireIn = 60;  //验证码失效时间
    private String url;           //图形验证码or短信验证码 拦截的url


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
