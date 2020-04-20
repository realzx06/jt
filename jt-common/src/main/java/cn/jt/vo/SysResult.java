package cn.jt.vo;

import sun.rmi.server.InactiveGroupException;

public class SysResult {
    private  Integer status;//状态码信息 200正确 201失败
    private String message;//返回给页面的信息
    private Object data;//返回的数据



    /*
     封装工具IPI方法
     */
    public static SysResult fail(){
        return new SysResult(201,"服务器异常",null);

    }
    public static SysResult success() {

        return new SysResult(200,"业务调用正常!", null);
    }

    public static SysResult success(Object data){
        return new SysResult(200,"业务成功",data);
    }

    public static SysResult success(String msg,Object data) {

        return new SysResult(200,msg, data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public SysResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
