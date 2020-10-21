package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRoleDao {

    int deleteObjectsByUserId(Integer userId);

    /**基于用户id找到用户对应的角色*/
    @Select("select role_id from sys_user_roles where user_id=#{userId}")
    List<Integer> findRoleIdsByUserId(Integer userId);

    int insertObjects(Integer userId,Integer[] roleIds);

    int deleteObjectsByRoleId(Integer roleId);
}
