package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogDao {
    /**
     * 基于日志记录id执行删除操作
     * @param ids
     * @return
     */
    int deleteObjects(Integer... ids);
    /**
     * 基于条件查询日志总记录数
     * @param username
     * @return
     */
    int getRowCount(@Param("username") String username);

    /**
     * 分页查询当前页用户日志数据
     * @param username 用户名
     * @param startIndex 起始位置
     * @param pageSize 页面大小（每页最多显示多少条记录）
     * @return 查询到的当前页的日志记录
     */
    List<SysLog> findPageObjects(@Param("username") String username,
                                 @Param("startIndex") Integer startIndex,
                                 @Param("pageSize") Integer pageSize);
}
