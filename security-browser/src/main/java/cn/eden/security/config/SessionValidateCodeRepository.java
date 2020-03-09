package cn.eden.security.config;

import cn.eden.code.ValidateCode;
import cn.eden.code.ValidateCodeRepository;
import cn.eden.code.controller.ValidateCodeController;
import cn.eden.code.image.ImageCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, String ValidateCodeKey,ValidateCode validateCode) {
        sessionStrategy.setAttribute(request,ValidateCodeKey,validateCode); //从request对象中获取session
    }

    @Override
    public ValidateCode get(ServletWebRequest request,String ValidateCodeKey) {
        ImageCode imageCode=(ImageCode) sessionStrategy.getAttribute(request, ValidateCodeKey);
        return imageCode;
    }

    @Override
    public void remove(ServletWebRequest request,String ValidateCodeKey) {
        sessionStrategy.removeAttribute(request, ValidateCodeKey);
    }
}
