package com.cy.pj.sys.dao;

import com.cy.pj.common.pojo.CheckBox;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.pojo.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRoleDao {
    @Select("select id,name from sys_roles")
    List<CheckBox> findObjects();

    SysRoleMenu findById(Integer id);

    int updateObject(SysRole entity);

    int deleteObject(Integer id);

    int insertObject(SysRole entity);

    /**
     * 基于条件统计所有角色信息
     * @param name
     * @return
     */
//    int getRowCount(String name);

//    /**
//     * 查询当前页数据
//     * @param name
//     * @param startIndex
//     * @param pageSize
//     * @return
//     */
//    List<SysRole> findPageObjects(String name,Integer startIndex,Integer pageSize);

    List<SysRole> findPageObjects(String name);
}
