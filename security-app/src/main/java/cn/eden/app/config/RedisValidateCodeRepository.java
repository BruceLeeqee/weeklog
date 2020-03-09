package cn.eden.app.config;

import cn.eden.code.ValidateCode;
import cn.eden.code.ValidateCodeRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Override
    public void save(ServletWebRequest request, String ValidateCodeKey, ValidateCode validateCode) {

    }

    @Override
    public ValidateCode get(ServletWebRequest request, String ValidateCodeKey) {
        return null;
    }

    @Override
    public void remove(ServletWebRequest request, String ValidateCodeKey) {

    }
}
