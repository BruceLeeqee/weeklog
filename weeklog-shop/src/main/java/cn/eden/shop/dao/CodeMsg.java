package cn.eden.shop.dao;

/**
 * 按模块定义code代码
 */
public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SUCCESS=new CodeMsg(200,"success");
    public static CodeMsg SERVER_ERROR =new CodeMsg(500,"服务端异常");
    //登陆模块5002xx

    //商品模块5003xx

    //订单模块5004xx

    //秒杀模块5005xx
    private CodeMsg(int code,String msg){
         this.code=code;
         this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
