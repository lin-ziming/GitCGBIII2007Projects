package com.cy.pj.goods.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**pojo:用于封装商品信息*/

@Data//@Setter+@Getter+@ToString
public class Goods {
    private Long id;//id bigint primary key auto_increment
    private String name;//name varchar(100) not null
    private String remark;//remark text
    //告诉spring web模块将对象转换为json串时，这个日期按照指定格式进行转换
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createdTime;//createdTime datetime

}
