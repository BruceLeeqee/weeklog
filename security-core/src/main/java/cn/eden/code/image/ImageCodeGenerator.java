package cn.eden.code.image;

import cn.eden.code.ValidateCodeGenerator;
import cn.eden.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * @author 李琦
 * 定制ImageCode生成方法
 * 不用@Component注解
 */
@Component("imageCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties; // @Autowired注释掉，在配置文件进行配置

    @Override
    public ImageCode createCode(ServletWebRequest request) {
        {
//          int width = securityProperties.getCode().getImageCode().getWidth(); //图片长
            int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",securityProperties.getCode().getImageCode().getWidth());
            int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height",securityProperties.getCode().getImageCode().getHeight());
            BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics g=image.getGraphics();
            // 生成随机条纹
            Random random=new Random();
            g.setColor(getRandColor(200,250)); //设置随机颜色
            g.fillRect(0,0,width,height); //设置图片坐标位置，以及宽高
            g.setFont(new Font("Times New Roman",Font.ITALIC,20)); //设置字体
            g.setColor(getRandColor(160,200));
            for(int i=0;i<155;i++){
                int x=random.nextInt(width);
                int y=random.nextInt(height);
                int x1=random.nextInt(12);
                int y1=random.nextInt(12);
                g.drawLine(x,y,x+x1,y+y1);
            }
            //生成4位随机数
            String sRand = "";
            for(int i=0;i<securityProperties.getCode().getImageCode().getLength();i++){
                String rand=String.valueOf(random.nextInt(10));
                g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
                g.drawString(rand,13*i+6,16);
                sRand+=rand;
            }
            g.dispose();
            //返回图形验证码—60是图形验证码有效期
            return new ImageCode(image,sRand,securityProperties.getCode().getImageCode().getExpireIn());
        }
    }
    /**
     * 生成随机背景条纹颜色
     * @return
     */
    private Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255){
            fc=255;
        }
        if(bc>255){
            bc=255;
        }
        int r = fc+random.nextInt(bc - fc);
        int g = fc+random.nextInt(bc - fc);
        int b = fc+random.nextInt(bc - fc);
        return new Color(r,g,b);
    }
    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
