package com.cy.pj.activity.dao;

import com.cy.pj.activity.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
/**活动模块的数据逻辑对象*/
@Mapper
public interface ActivityDao {

    @Select("select * from tb_activity order by createdTime desc")
    List<Activity> findActivity();

    int insertObject(Activity activity);

    @Update("update tb_activity set state=0 where id=#{id}")
    int updateState(Long id);
}
