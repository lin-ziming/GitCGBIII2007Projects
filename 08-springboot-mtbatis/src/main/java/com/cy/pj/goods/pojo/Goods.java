package com.cy.pj.goods.pojo;

import java.util.Date;
/**用于存储商品信息的pojo对象*/
public class Goods {
    private Integer id;//若数据库存储的是bigint，则用Long。
    private String name;//属性不要用基本数据类型
    private String remark;
    private Date createdTime;

    public Goods(Integer id, String name, String remark, Date createdTime) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
