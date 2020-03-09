package cn.eden.service;

import cn.eden.properties.SecurityProperties;
import cn.eden.dto.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author 李琦
 * 需要身份认证的请求跳转登录页url
 * 如果是Html请求，则跳转到登陆页面
 * 如果是Restful请求，则返回json响应数据
 * 目的：保证Restful请求返回json数据，不反回登陆页
 */
@Controller
public class BrowserSecurityController {
    private static Logger logger= LoggerFactory.getLogger(BrowserSecurityController.class);

    private RequestCache requestCache=new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 访问保护资源时 登陆页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/auth/determine")
    @ResponseStatus(code= HttpStatus.UNAUTHORIZED) //401状态吗
    @ResponseBody
    public SimpleResponse determineBatch(HttpServletRequest request, HttpServletResponse response)throws Exception{
        SavedRequest request1=requestCache.getRequest(request,response);
        if(request1 != null){
            String targetUrl=request1.getRedirectUrl().toString();
            logger.info("首次发送请求的URL是"+targetUrl); //request请求：跳转登陆页面请求  request1:首次登陆请求
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginpage()); //标准登录页 or 自定义登录页
            }
        }
        return new SimpleResponse("访问需要登陆认证，请引导到登录页");
    }
}
