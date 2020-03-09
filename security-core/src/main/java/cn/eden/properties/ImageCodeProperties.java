package cn.eden.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class ImageCodeProperties extends SmsCodeProperties{
    private int width = 67; //图形验证码图片宽度
    private int height = 23; //图形验证码图片高度


    public ImageCodeProperties() {
        setLength(4);
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
