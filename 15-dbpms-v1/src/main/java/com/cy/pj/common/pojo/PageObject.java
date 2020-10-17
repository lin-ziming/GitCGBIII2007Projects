package com.cy.pj.common.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**借助此对象在业务层封装业务逻辑结果*/
@Data
@NoArgsConstructor
public class PageObject<T> implements Serializable {//类名<泛型>==>类泛型-->约束类中属性，方法参数以及返回值类型

    private static final long serialVersionUID = -5307436796659944757L;
    /**总记录数*/
    private Integer rowCount;
    /**当前页记录*/
    private List<T> records;
    /**总页数(计算出来)*/
    private Integer pageCount;
    /**当前页码值*/
    private Integer pageCurrent;
    /**页面大小（每页最多显示多少条记录）*/
    private Integer pageSize;

    public PageObject(Integer rowCount, List<T> records, Integer pageCurrent, Integer pageSize) {
        this.rowCount = rowCount;
        this.records = records;
        this.pageCurrent = pageCurrent;
        this.pageSize = pageSize;
        this.pageCount = rowCount/pageSize;
        if (this.rowCount%this.pageSize!=0) this.pageCount++;
    }
}
