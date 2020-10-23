package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public int updateObject(SysMenu entity) {
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("菜单名字不能为空");
        int rows = sysMenuDao.updateObject(entity);
        if (rows==0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }

    @Override
    public int saveObject(SysMenu entity) {
        //1.参数校验
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("菜单名不能为空");
        //2.保存数据
        int rows = sysMenuDao.insertObject(entity);
        //3.返回数据
        return rows;
    }

    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenuDao.findZtreeMenuNodes();
    }

    @Override
    public int deleteObject(Integer id) {
        //1.参数校验
        if(id==null || id<1)
            throw new IllegalArgumentException("id值无效");
        //2.判定菜单是否有子菜单，若有，则不允许删除
        int childCount=sysMenuDao.getChildCount(id);
        if(childCount>0)
            throw new ServiceException("请先删除子菜单");
        //3.删除关系数据
        sysRoleMenuDao.deleteObjectsByMenuId(id);
        int rows = sysMenuDao.deleteObject(id);
        //4.删除自身数据
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }

    @Cacheable("menuCache") //这个注解描述的方法为一个切入点方法
    // @RequiredLog("查询菜单")
    @Override
    public List<Map<String, Object>> findObjects() {
        System.out.println("menu.findObjects()");
        return sysMenuDao.findObjects();
    }
}
