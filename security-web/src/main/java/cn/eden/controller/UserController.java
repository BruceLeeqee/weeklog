package cn.eden.controller;

import cn.eden.repository.entity.User;
import cn.eden.repository.entity.WebResult;
import cn.eden.repository.hibernateRepo.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 *  提供Rest API的控制器
 *  PageAble分页模板: number size sort    @PageDefault
 *  RequestParam(name="username",required=false,defaultValue="xiangziyou") 将参数传递给方法中的参数
 *  RequestBody  将请求体 映射到 方法中的参数   enctype必须是appication/json格式
 *  PathVariable 将请求url 映射到 方法中的参数  @JsonView 控制json输出内容(1.使用接口声明多个视图2. get方法上指定视图 3. Controller方法上指定视图)
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger=LoggerFactory.getLogger(UserController.class); //必须有参数接收！！！

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/me")
    public Object getCurrentUser(){
        return WebResult.success(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class)
    public List<User> query(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
//    @JsonView(User.UserSimpleView.class)
    @ResponseBody
    public WebResult create(HttpServletRequest request){ //当@Valid效验出错时，用BindingResult接收错误信息
        User user=new User();
        user.setNickname(request.getParameter("nickname"));
        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("password"));
        User result= userRepository.save(user);
        return WebResult.success(result);
    }

    @JsonView(User.UserDetailView.class)
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User getUserinfo(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @RequestMapping(value = "/user/{id}/{password}",method = RequestMethod.PUT)
    public void update(@PathVariable long id,@PathVariable String password){
         userRepository.update(id,password);
    }

    @RequestMapping(value="/user/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        User user=new User();
        user.setId(id);
        userRepository.delete(user);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "hello world";
    }

    /*@RequestMapping(value = "/user",method = RequestMethod.POST)
    @JsonView(User.UserSimpleView.class)
    public User create(@Valid @RequestBody User user,BindingResult errors){ //当@Valid效验出错时，用BindingResult接收错误信息
        if (errors.hasErrors()){
            //logger.info(errors.getAllErrors().stream().forEach(););
            errors.getAllErrors().stream().forEach(error->{
                logger.info(error.toString());
            });
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }//当@Valid效验出错时，用BindingResult接收错误信息
*/
}
