package cn.eden.shop.dao;

public class WebResult<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> WebResult<T> success(T data){
        return new WebResult<>(data);
    }

    public static <T> WebResult<T> error(CodeMsg codeMsg){
        return new WebResult<T>(codeMsg);
    }

    private WebResult(T data){
        this.code=200;
        this.msg="success";
        this.data=data;
    }
    private WebResult(CodeMsg codeMsg){
        if(codeMsg==null){
            return;
        }
        this.code=codeMsg.getCode();
        this.msg=codeMsg.getMsg();
    }
    public int getCode() {
        return code;
    }

//    public void setCode(int code) {
//        this.code = code;
//    }

    public String getMsg() {
        return msg;
    }

//    public void setMsg(String msg) {
//        this.msg = msg;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
