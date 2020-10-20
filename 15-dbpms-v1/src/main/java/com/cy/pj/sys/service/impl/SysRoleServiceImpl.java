package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.common.util.ValidUtils;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @RequiredLog("删除角色")
    @Override
    public int deleteObject(Integer id) {
        ValidUtils.isArgsValid(id==null || id<0, "请选择");
        sysRoleMenuDao.deleteObjectsByRoleId(id);
        sysUserRoleDao.deleteObjectsByRoleId(id);
        int rows = sysRoleDao.deleteObject(id);
        ValidUtils.isResultValid(rows==0, "此记录可能不存在");
        return rows;
    }

    @RequiredLog("保存角色")
    @Override
    public int saveObject(SysRole entity, Integer[] menuIds) {
        ValidUtils.isArgsValid(entity==null, "保存角色不能为空");
        ValidUtils.isArgsValid(entity.getName()==null || entity.getName()=="", "角色名不能为空");
        ValidUtils.isArgsValid(menuIds==null || menuIds.length==0, "必须为角色权限");
        //2.保存角色自身信息
        System.out.println("entity.id="+entity.getId());
        int rows=sysRoleDao.insertObject(entity);
        System.out.println("entity.id="+entity.getId());
        //3.保存角色对应的菜单信息
        sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
        return rows;
    }

    @RequiredLog("查询角色")
    @Override
    public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
        //1.参数校验
        //if(pageCurrent==null||pageCurrent<1)
        //throw new IllegalArgumentException("当前页码值不正确");
        ValidUtils.isArgsValid(pageCurrent==null || pageCurrent<1, "当前页码值无效");
        //2.查询总记录数并校验
        int rowCount = sysRoleDao.getRowCount(name);
        //if(rowCount==0)
        // throw new ServiceException("没有找到到对应记录");
        ValidUtils.isResultValid(rowCount==0, "没有找到对应的记录");
        //3.查询当前页记录
        int pageSize=10;
        int startIndex=(pageCurrent-1)*pageSize;
        List<SysRole> records=sysRoleDao.findPageObjects(name, startIndex, pageSize);
        //4.对查询结果进行封装
        return new PageObject<>(rowCount, records, pageCurrent, pageSize);
    }
}
