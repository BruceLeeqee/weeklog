package cn.eden.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class ValidateCodeProperties {
    //图形验证码配置
    private ImageCodeProperties imageCode=new ImageCodeProperties();
    //短信验证码配置
    private SmsCodeProperties smsCode=new SmsCodeProperties();



    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
    }

    public SmsCodeProperties getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SmsCodeProperties smsCode) {
        this.smsCode = smsCode;
    }
}
