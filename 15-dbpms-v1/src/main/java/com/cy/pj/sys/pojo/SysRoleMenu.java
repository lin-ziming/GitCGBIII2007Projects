package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**通过此对象封装查询到的角色和菜单信息
 * 需求：假如现在要基于角色id从数据库查询角色以及角色对菜单信息封装到此对象，要用什么查询方案？
 * 分析：需要的数据在哪里？答：sys_roles,sys_role_menus
 * 方案：
 * 1)业务层发起多次单表查询(例如先查询角色，再查询菜单) 不足：每次都要从池中获取连接
 * 2)业务层发起一次查询，数据层执行表嵌套查询
 * 3)业务层发起一次查询，数据层执行多表关联(join)查询
 * */
@Data
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -2671028987524519218L;
    private Integer id;
    private String name;
    private String note;
    /**角色对象的菜单id*/
    private List<Integer> menuIds;
}
