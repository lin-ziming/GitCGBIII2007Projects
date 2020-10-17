package com.cy.pj.sys.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * pojo对象
 * FAQ?
 * 1)序列化：将对象转换为字节的过程，转换以后便于通过网络进行传输或存储到相关介质中
 * 2)反序列化：将字节转换为对象的过程
 * 官方建议：在java中所有用于存储数据的对象都实现Serializable接口，并添加序列化的ID
 */
@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;//用户名
    private String operation;//用户操作
    private String method;//请求方法
    private String params;//请求参数
    private Long time;//执行时长(毫秒)
    private String ip;//IP地址
    private Date createdTime;//创建时间
}
