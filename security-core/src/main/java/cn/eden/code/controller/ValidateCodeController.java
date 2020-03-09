package cn.eden.code.controller;

import cn.eden.code.ValidateCode;
import cn.eden.code.ValidateCodeGenerator;
import cn.eden.code.ValidateCodeRepository;
import cn.eden.code.image.ImageCode;
import cn.eden.code.sms.SmsSender;
import cn.eden.dto.SimpleResponse;
import cn.eden.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletWebRequest;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @author 李琦
 *  验证码重构：
 *  1. 基本参数可配置: 请求级配置——>应用级配置(web)——>默认级配置(core)
 *  2. 验证码生成逻辑可配置
 *  3. 拦截接口可配置
 */
@Controller
public class ValidateCodeController {

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    ValidateCodeRepository validateCodeRepository;

    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    public static final String SESSION_IMAGE_KEY="SESSION_KEY_IMAGE_CODE";

    public static final String SESSION_SMS_KEY="SESSION_KEY_SMS_CODE";
    /**
     * 图形验证码controller
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
         ImageCode imageCode= (ImageCode) imageCodeGenerator.createCode(new ServletWebRequest(request));
         validateCodeRepository.save(new ServletWebRequest(request),SESSION_IMAGE_KEY,imageCode);
         ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream()); //把验证码图片 写到输出流中
    }
    /**
     * 短信验证码controller
     * 重定向后，不向后执行了
     * 重定向后，session中的验证码改变了！！！
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/code/sms")
    public SimpleResponse createSmsCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ValidateCode validateCode=smsCodeGenerator.createCode(new ServletWebRequest(request));
        validateCodeRepository.save(new ServletWebRequest(request),SESSION_SMS_KEY,validateCode);
        String mobile= ServletRequestUtils.getRequiredStringParameter(request,"smscode"); //从前端获取手机号
        boolean flag=smsSender.send(mobile,validateCode.getCode());
        if(flag){
            redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginpage());
        }
        return new SimpleResponse("发送短信验证码出现错误");
    }
}
