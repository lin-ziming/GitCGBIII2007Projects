package com.cy.pj.sys.dao;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.pojo.SysMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuDao {

    int updateObject(SysMenu entity);

    int insertObject(SysMenu entity);

    @Select("select id,name,parentId from sys_menus")
    List<Node> findZtreeMenuNodes();

    @Select("select count(*) from sys_menus where parentId=#{id}")
    int getChildCount(Integer id);

    @Delete("delete from sys_menus where id=#{id}")
    int deleteObject(Integer id);

    /**
     * 方法功能：查询所有菜单信息
     * 用map的缺点：不知道map里面存了什么字段，值得类型不可控。
     * 一般外包项目为了赶进度，会用map
     * @return
     */
    List<Map<String,Object>> findObjects();
}
