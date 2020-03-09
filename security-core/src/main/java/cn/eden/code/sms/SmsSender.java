package cn.eden.code.sms;

public interface SmsSender {
    boolean send(String mobile,String code);
}
