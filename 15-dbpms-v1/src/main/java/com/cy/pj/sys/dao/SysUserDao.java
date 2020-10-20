package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysUserDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao {

    int getRowCount(String username);

    List<SysUserDept> findPageObjects(String name,Integer startIndex,Integer pageSize);
}
