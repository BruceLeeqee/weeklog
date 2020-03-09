package cn.eden.security.resulthandler;

import cn.eden.repository.entity.WebResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static Logger logger= LoggerFactory.getLogger(CustomizeAuthenticationFailureHandler.class);

    ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
          response.setContentType("application/json;charset=UTF-8");
          logger.info(objectMapper.writeValueAsString(exception.getMessage()));
          response.getWriter().write(objectMapper.writeValueAsString(WebResult.error(exception.getMessage())));
          return;//失败了，就不往下走
    }
}
