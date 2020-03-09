package cn.eden.repository.entity;

/**
 * 按模块定义code代码
 */
public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SUCCESS=new CodeMsg(200,"success");
    public static CodeMsg SERVER_ERROR =new CodeMsg(500,"服务端异常");
    //登陆模块500xxgraph
    public static CodeMsg GRAPH_CODE_NULL =new CodeMsg(500,"验证码不能为空");
    public static CodeMsg GRAPH_CODE_NOT_GEN =new CodeMsg(500,"验证码未生成");
    public static CodeMsg GRAPH_CODE_EXPIRE =new CodeMsg(500,"您输入的验证码已过期");
    public static CodeMsg GRAPH_CODE_NOT_MATCH =new CodeMsg(500,"您输入的验证码不匹配");
    //商品模块501xx

    //订单模块502xx

    //秒杀模块503xx
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
