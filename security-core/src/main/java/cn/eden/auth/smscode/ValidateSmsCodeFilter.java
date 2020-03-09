package cn.eden.auth.smscode;

import cn.eden.code.ValidateCodeRepository;
import cn.eden.code.image.ImageCode;
import cn.eden.properties.SecurityProperties;
import cn.eden.code.ValidateCode;
import cn.eden.code.controller.ValidateCodeController;
import cn.eden.code.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *  效验短信验证码是否匹配
 */
public class ValidateSmsCodeFilter extends OncePerRequestFilter implements InitializingBean {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    ValidateCodeRepository validateCodeRepository;

    AntPathMatcher antPathMatcher=new AntPathMatcher();

    AuthenticationManager authenticationManager;

    private AuthenticationFailureHandler authenticationFailureHandler;

    private Set<String> urls=new HashSet<>(); //图形验证码过滤器  拦截得url集合

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urls.add("/login/smscode");
        String[] strs=StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSmsCode().getUrl(),",");
        if(strs == null){
            return;
        }
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
    void validate(ServletWebRequest request) throws ServletRequestBindingException { //抛出隐含异常
        ValidateCode validateCode=validateCodeRepository.get(request,ValidateCodeController.SESSION_SMS_KEY);
        String inputCode= ServletRequestUtils.getStringParameter(request.getRequest(),"smsCode");
        if(StringUtils.isEmpty(inputCode)){
            throw new ValidateCodeException("您输入的短信验证码不能为空"); //手动抛出异常
        }
        if(validateCode==null){
            throw new ValidateCodeException("请重新发送验证码"); //手动抛出异常
        }
        if(validateCode.isExpire()){
            throw new ValidateCodeException("您输入的短信验证码不能为空"); //手动抛出异常
        }
        if(! inputCode.equals(validateCode.getCode())){
            throw new ValidateCodeException("您输入的短信验证码不匹配"); //手动抛出异常
        }
        validateCodeRepository.remove(request,ValidateCodeController.SESSION_SMS_KEY);
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
