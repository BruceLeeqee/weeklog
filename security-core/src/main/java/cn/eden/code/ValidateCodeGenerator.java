package cn.eden.code;

import org.springframework.web.context.request.ServletWebRequest;
/**
 * @author 李琦
 * 接口的实现 可配置
 * 不通过注解，而通过set方法设置的好处: 可配置，灵活
 */
public interface ValidateCodeGenerator {
     ValidateCode createCode(ServletWebRequest request);
}
