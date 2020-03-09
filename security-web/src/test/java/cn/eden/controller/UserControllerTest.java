//package cn.eden.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@RunWith(SpringRunner.class) //如何运行测试用例
//@SpringBootTest              //标识测试用例
//public class UserControllerTest {
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc; //伪造mvc环境
//
//    @Before                  //每次执行测试用例之前执行
//    public void setup(){
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//    @Test
//    public void whenQuerySuccess() throws Exception{
////        mockMvc.perform(MockMvcRequestBuilders.get("/user").contentType(MediaType.APPLICATION_JSON_UTF8)) //模拟发送get请求
////               .andExpect(MockMvcResultMatchers.status().isOk()) //期望返回结果状态码为200
////               .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
