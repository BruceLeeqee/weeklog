package cn.eden.auth.imgcode;

import cn.eden.code.ValidateCodeRepository;
import cn.eden.code.image.ImageCode;
import cn.eden.properties.SecurityProperties;
import cn.eden.code.controller.ValidateCodeController;
import cn.eden.code.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 李琦
 * 效验图片验证码
 * 实现InitializingBean后目的：其他参数初始化完毕后，再初始化url得值
 */
public class ValidateImageCodeFilter extends OncePerRequestFilter implements InitializingBean{

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    ValidateCodeRepository validateCodeRepository;

    AntPathMatcher antPathMatcher=new AntPathMatcher();

    private AuthenticationFailureHandler authenticationFailureHandler;

    private Set<String> urls=new HashSet<>(); //图形验证码过滤器  拦截得url集合

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] strs=StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImageCode().getUrl(),",");
        for (String str: strs) {
            urls.add(str);
        }
    }

    /**
     * 区分url 与 uri
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Boolean flag=false;
        String temp=request.getRequestURI();
        for (String str : urls) {
            if(antPathMatcher.match(request.getRequestURI(),str)){
                flag=true;
            }
        }
        if(flag){  //若图形验证码过滤器 拦截此url
            try {
                validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){  //自定义异常类型
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
    /**
     * @param request
     * 效验验证码是否有效
     * 1. 从session中获取验证码ImageCode
     * 2. 检查其是否过期
     * 3. 检查其与用户输入的验证码是否匹配,若不匹配移除session中的验证码
     */
    void validate(ServletWebRequest request) throws ServletRequestBindingException{ //抛出隐含异常
        ImageCode imageCode=(ImageCode)validateCodeRepository.get(request,ValidateCodeController.SESSION_IMAGE_KEY);

        String inputCode=ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode");
        if(StringUtils.isEmpty(inputCode)){
            throw new ValidateCodeException("您输入的验证码不能为空"); //手动抛出异常
        }
        if(imageCode==null){
            throw new ValidateCodeException("请重新刷新页面"); //手动抛出异常
        }
        if(imageCode.isExpire()){
            throw new ValidateCodeException("您输入的验证码已过期"); //手动抛出异常
        }
        if(! inputCode.equals(imageCode.getCode())){
            throw new ValidateCodeException("您输入的验证码不匹配"); //手动抛出异常
        }
        validateCodeRepository.remove(request,ValidateCodeController.SESSION_IMAGE_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
