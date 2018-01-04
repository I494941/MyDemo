package cn.tsingpro.retrofit2_rxjava2.http.bean;

/**
 * @author yemao
 * @date 2017/4/9
 * @description 解析实体基类!
 */

public class BaseEntity<T> {
    private static int SUCCESS_CODE=42440;//成功的code
    private int    code;
    private String msg;
    private T      data;


    public boolean isSuccess(){
        return getCode()==SUCCESS_CODE;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }





}
