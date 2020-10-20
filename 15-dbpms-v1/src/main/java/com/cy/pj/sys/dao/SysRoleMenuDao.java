package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRoleMenuDao {

    @Select("select menu_id from sys_role_menus where id=#{roleId}")
    List<Integer> findMenuIdsByRoleId(Integer roleId);

    int insertObjects(Integer roleId,Integer[] menuIds);

    int deleteObjectsByRoleId(Integer roleId);

    /**基于菜单id执行关系数据的删除*/
    @Delete("delete from sys_role_menus where menu_id=#{menuId}")
    int deleteObjectsByMenuId(Integer menuId);
}
