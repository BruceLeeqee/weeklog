package cn.eden.security.resulthandler;

import cn.eden.repository.entity.WebResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customizeAuthenticationSuccessHandler")
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private static Logger logger= LoggerFactory.getLogger(CustomizeAuthenticationSuccessHandler.class);

    @Autowired
    ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
          logger.info("登陆成功");
          response.setContentType("application/json;charset=UTF-8");
          response.getWriter().write(objectMapper.writeValueAsString(WebResult.success(authentication.getPrincipal())));
    }





















}
