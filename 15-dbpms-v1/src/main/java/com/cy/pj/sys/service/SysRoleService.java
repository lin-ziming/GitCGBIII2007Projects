package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.pojo.SysRoleMenu;

public interface SysRoleService {

    SysRoleMenu findById(Integer id);

    int deleteObject(Integer id);

    /**
     * 更新角色以及角色对应菜单信息
     * @param entity
     * @param menuIds
     * @return
     */
    int updateObject(SysRole entity,Integer[] menuIds);

    /**
     * 保存角色以及角色对应菜单信息
     * @param entity
     * @param menuIds
     * @return
     */
    int saveObject(SysRole entity,Integer[] menuIds);

    /**
     * 分页查询角色信息
     * @param name
     * @param pageCurrent
     * @return
     */
    PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
}
