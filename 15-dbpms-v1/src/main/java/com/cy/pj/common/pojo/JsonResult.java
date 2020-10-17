package com.cy.pj.common.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**封装服务端响应到客户端的数据，通过此对象定义一种规范的格式*/
@Data
@NoArgsConstructor
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -868996739646416767L;
    /**状态码：1表示ok,0表示error*/
    private int state=1;
    /**状态信息*/
    private String message="ok";
    /**正确数据*/
    private Object data;

    public JsonResult(String message){
        this.message=message;
    }
    /**一般查询时调用，封装查询结果*/
    public JsonResult(Object data){
        this.data=data;
    }
    /**出现异常时调用*/
    public JsonResult(Throwable t){
        this.state=0;
        this.message=t.getMessage();
    }
}
