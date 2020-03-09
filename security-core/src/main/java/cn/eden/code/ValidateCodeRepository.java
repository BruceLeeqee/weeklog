package cn.eden.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 *  对验证码处理的接口
 *     图形验证码
 *     短信验证码
 */
public interface ValidateCodeRepository {
    //保存验证码
    void save(ServletWebRequest request,String ValidateCodeKey,ValidateCode validateCode);
    //获取验证码
    ValidateCode get(ServletWebRequest request,String ValidateCodeKey);
    //移除验证码
    void remove(ServletWebRequest request,String ValidateCodeKey);
}
