package com.cy.pj.goods.pojo;

import lombok.Data;

import java.util.Date;
/**pojo:用于封装商品信息*/

@Data//@Setter+@Getter+@ToString
public class Goods {
    private Long id;//id bigint primary key auto_increment
    private String name;//name varchar(100) not null
    private String remark;//remark text
    private Date createdTime;//createdTime datetime

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
