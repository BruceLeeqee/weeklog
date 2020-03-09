package cn.eden.shop.controller;

import cn.eden.shop.dao.CodeMsg;
import cn.eden.shop.dao.WebResult;
import cn.eden.shop.domain.Good;
import cn.eden.shop.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodsController {

    @Autowired
    RedisService redisService;

    @RequestMapping("/test1")
    @ResponseBody
    public WebResult<String> test1(){
        String value=redisService.get("qiqi",String.class);
        return WebResult.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/test2")
    @ResponseBody
    public WebResult<String> test2(){
        String value=redisService.get("name",String.class);
        return WebResult.success("成功");
    }

    @RequestMapping("/test3")
    @ResponseBody
    public Good test3(){
        return  new Good("1232","12");
    }
}
