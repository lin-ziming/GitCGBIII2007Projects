package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.pojo.SysUserDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SysUserDao {
    @Update("update sys_users set password=#{password},salt=#{salt},modifiedTime=now() where id=#{id}")
    int updatePassword(String password,String salt,Integer id);

    /**查询当前用户以及用户对应的部门信息*/
    SysUserDept findObjectById(Integer id);

    int updateObject(SysUser entity);
    int insertObject(SysUser entity);

    @Update("update sys_users set valid=#{valid},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}")
    int validById(Integer id, Integer valid, String modifiedUser);

    int getRowCount(String username);

    List<SysUserDept> findPageObjects(String username,Integer startIndex,Integer pageSize);
}
