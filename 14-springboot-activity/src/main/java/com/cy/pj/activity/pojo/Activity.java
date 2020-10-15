package com.cy.pj.activity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
/**活动模块的pojo对象，用于存储活动信息*/
@Data
public class Activity {
    private Integer id;
    private String title;
    private String category;
    //@JsonFormat 用于告诉spring，将值转换成json串时按指定格式进行值的转换
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date startTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date endTime;
    private String remark;
    private Integer state=1;
    private Date createdTime;
    private String createdUser;
}
