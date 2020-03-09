package cn.eden.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "auth-register";
    }
}
