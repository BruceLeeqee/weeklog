package cn.eden.code.image;

import cn.eden.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author 李琦
 * 生成图形验证码接口: 1. 根据随机数生成图片，并响应到登陆页面中去  2. 将随机数存到Session中
 * 图形验证码效验
 */
public class ImageCode extends ValidateCode {
    private BufferedImage image; //验证码图片

    public ImageCode(BufferedImage image, String code, int expireTimeLast) {
        super(code,expireTimeLast);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
