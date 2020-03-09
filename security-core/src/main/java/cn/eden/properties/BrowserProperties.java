package cn.eden.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class BrowserProperties {
    private String successPage="/auth-home.html"; // 登陆成功跳转页面

    private String registerpage="/auth-register.html"; //注册页面

    private String loginpage="/auth-login.html"; //登陆页面

    private String loginProcessUrl="/user/login"; //登陆处理控制器

    private String registerProcessUrl="/user/register"; //注册处理控制器

    private int rememeberMeSeconds = 3600; //默认记住我时间

    public String getLoginpage() {
        return loginpage;
    }

    public void setLoginpage(String loginpage) {
        this.loginpage = loginpage;
    }

    public String getRegisterpage() {
        return registerpage;
    }

    public void setRegisterpage(String registerpage) {
        this.registerpage = registerpage;
    }

    public String getRegisterProcessUrl() {
        return registerProcessUrl;
    }

    public void setRegisterProcessUrl(String registerProcessUrl) {
        this.registerProcessUrl = registerProcessUrl;
    }

    public String getLoginProcessUrl() {
        return loginProcessUrl;
    }

    public void setLoginProcessUrl(String loginProcessUrl) {
        this.loginProcessUrl = loginProcessUrl;
    }

    public String getSuccessPage() {
        return successPage;
    }

    public void setSuccessPage(String successPage) {
        this.successPage = successPage;
    }


    public int getRememeberMeSeconds() {
        return rememeberMeSeconds;
    }

    public void setRememeberMeSeconds(int rememeberMeSeconds) {
        this.rememeberMeSeconds = rememeberMeSeconds;
    }
}
