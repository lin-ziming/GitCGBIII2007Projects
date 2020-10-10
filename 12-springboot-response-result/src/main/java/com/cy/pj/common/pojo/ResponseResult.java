package com.cy.pj.common.pojo;

/**
 * 基于此对象封装服务端响应到客户端的数据
 */
public class ResponseResult {
    /**响应状态码（有的人用code）*/
    private Integer state=1;//1表示ok,0表示error,...
    /**状态码对应的信息*/
    private  String message="ok";
    /**正确的响应数据*/
    private Object data;

    public ResponseResult(){}

    public ResponseResult(String message){//new ResponseResult("delete ok");
        this.message=message;
    }

    public ResponseResult(Object data){//new ResponseResult(list);
        this.data=data;
    }
    public ResponseResult(Throwable e){//new ResponseResult(e);
        this.state=0;
        this.message=e.getMessage();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
}
