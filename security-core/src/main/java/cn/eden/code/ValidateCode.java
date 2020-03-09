package cn.eden.code;

import java.time.LocalDateTime;

public class ValidateCode {
    private String code; //图形验证码/短信验证码

    private LocalDateTime expireTime;//过期时间点

    public ValidateCode(String code, int expireTimeLast) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTimeLast);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 判断是否过期
     * @return
     */
    public boolean isExpire(){
        if(LocalDateTime.now().isAfter(expireTime)){
            return true;
        }
        return false;
    }
}
