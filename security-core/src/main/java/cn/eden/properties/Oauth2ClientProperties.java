package cn.eden.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class Oauth2ClientProperties {
    private String clientId;
    private String clientSecret;
    private int accessTokenValidatySeconds=3600;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getAccessTokenValidatySeconds() {
        return accessTokenValidatySeconds;
    }

    public void setAccessTokenValidatySeconds(int accessTokenValidatySeconds) {
        this.accessTokenValidatySeconds = accessTokenValidatySeconds;
    }
}
