package cn.eden.social.qq;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    @Override
    public QQUserInfo getUserInfo() {
        return null;
    }
}
