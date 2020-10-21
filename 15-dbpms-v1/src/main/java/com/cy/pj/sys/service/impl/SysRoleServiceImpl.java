package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.pojo.CheckBox;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.common.util.ValidUtils;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.pojo.SysRoleMenu;
import com.cy.pj.sys.service.SysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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


    @Override
    public List<CheckBox> findObjects() {
        return sysRoleDao.findObjects();
    }

    @Override
    public SysRoleMenu findById(Integer id) {
        //1.参数校验
        ValidUtils.isArgsValid(id==null ||id<1, "id值不正确");
        //2.查询角色自身信息
        SysRoleMenu roleMenu = sysRoleDao.findById(id);//connection
        //3.查询角色对应的菜单id并进行封装
//        List<Integer> menuIds = sysRoleMenuDao.findMenuIdsByRoleId(id);//connection
//        roleMenu.setMenuIds(menuIds);
        //4.返回查询结果
        return roleMenu;
    }

    /**分别进行单表查询，然后在业务层进行封装
     * 不足：每次都要从池中获取连接*/
//    @Override
//    public SysRoleMenu findById(Integer id) {
//        //1.参数校验
//        ValidUtils.isArgsValid(id==null ||id<1, "id值不正确");
//        //2.查询角色自身信息
//        SysRoleMenu roleMenu = sysRoleDao.findById(id);//connection
//        //3.查询角色对应的菜单id并进行封装
//        List<Integer> menuIds = sysRoleMenuDao.findMenuIdsByRoleId(id);//connection
//        roleMenu.setMenuIds(menuIds);
//        //4.返回查询结果
//        return roleMenu;
//    }

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

    @Override
    public int updateObject(SysRole entity, Integer[] menuIds) {
        ValidUtils.isArgsValid(entity==null, "保存角色不能为空");
        ValidUtils.isArgsValid(entity.getName()==null || entity.getName()=="", "角色名不能为空");
        ValidUtils.isArgsValid(menuIds==null || menuIds.length==0, "必须为角色权限");
        //2.保存角色自身信息
        int rows=sysRoleDao.insertObject(entity);
        //3.保存角色对应的菜单信息
        sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
        sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
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
//        int rowCount = sysRoleDao.getRowCount(name);
        //if(rowCount==0)
        // throw new ServiceException("没有找到到对应记录");
//        ValidUtils.isResultValid(rowCount==0, "没有找到对应的记录");
        //3.查询当前页记录
        int pageSize=3;
//        int startIndex=(pageCurrent-1)*pageSize;
        //启动pageHelper拦截器
        Page<SysRole> page = PageHelper.startPage(pageCurrent, pageSize);
        List<SysRole> records=sysRoleDao.findPageObjects(name/*,startIndex, pageSize*/);
        //4.对查询结果进行封装
        return new PageObject<>(/*rowCount*/(int)page.getTotal(), records, pageCurrent, pageSize);
    }
}
