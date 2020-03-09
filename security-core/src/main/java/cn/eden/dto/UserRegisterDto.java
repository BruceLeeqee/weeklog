package cn.eden.dto;

import org.springframework.stereotype.Component;

@Component
public class UserRegisterDto {
    private String name;
    private String userename;
    private String phone;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserename() {
        return userename;
    }

    public void setUserename(String userename) {
        this.userename = userename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
