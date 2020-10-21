package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.common.util.ValidUtils;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.pojo.SysUserDept;
import com.cy.pj.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public int updatePassword(String password, String newPassword, String cfgPassword) {
        ValidUtils.isArgsValid(newPassword==null||newPassword=="","新密码不能为空");
        ValidUtils.isArgsValid(cfgPassword==null||cfgPassword=="","确认密码不能为空");
        ValidUtils.isArgsValid(newPassword!=cfgPassword,"两次输入的密码不相同");
        //判断原密码是否正确
        ValidUtils.isArgsValid(password==null||password=="","原密码不能为空");
        //获取登录用户
        SysUser user=(SysUser) SecurityUtils.getSubject().getPrincipal();
        SimpleHash sh=new SimpleHash("MD5",password, user.getSalt(), 1);
        if(!user.getPassword().equals(sh.toHex()))
            throw new IllegalArgumentException("原密码不正确");
        //对新密码进行加密
        String salt=UUID.randomUUID().toString();
        sh=new SimpleHash("MD5", newPassword, salt,1);
        //将新密码加密以后的结果更新到数据库
        int rows=sysUserDao.updatePassword(sh.toHex(), salt, user.getId());
        if(rows==0)
            throw new ServiceException("修改失败");
        return rows;
    }

    @Override
    public int updateObject(SysUser entity, Integer[] roleIds) {
        ValidUtils.isArgsValid(entity==null, "保存对象不能为空");
        String username = entity.getUsername();
        ValidUtils.isArgsValid(username==null || "".equals(username),"用户名不能为空");
        ValidUtils.isArgsValid(roleIds==null || roleIds.length==0,"必须为其指定角色");
        ValidUtils.isArgsValid(sysUserDao.getRowCount(entity.getUsername())>0,"用户名已存在");
        int rows=sysUserDao.updateObject(entity);
        Integer userId = entity.getId();
        sysUserRoleDao.deleteObjectsByUserId(userId);
        sysUserRoleDao.insertObjects(userId,roleIds);
        return rows;
    }

    @Override
    public Map<String, Object> findObjectById(Integer id) {
        //1.参数校验
        ValidUtils.isArgsValid(id==null || id<1, "ID不合法");
        //2.查询用户以及用户对应的部门信息
        SysUserDept user = sysUserDao.findObjectById(id);
        ValidUtils.isResultValid(user==null, "此用户不存在");
        //3.查询用户对应的角色信息
        List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(id);
        //4.封装查询结果
        Map<String,Object> map=new HashMap<>();
        map.put("user", user);
        map.put("roleIds", roleIds);
        return map;
    }

    /**
     * MD5加密算法：消息摘要算法
     * 1)不可逆(严格来说只能加密，不能解密)
     * 2)相同内容加密结果也相同
     */
    @Override
    public int saveObject(SysUser entity, Integer[] roleIds) {
        long start=System.currentTimeMillis();
        log.info("start:"+start);
        //1.参数校验
        ValidUtils.isArgsValid(entity==null, "保存对象不能为空");
        String username=entity.getUsername();
        ValidUtils.isArgsValid(username==null || "".equals(username),"用户名不能为空");
        ValidUtils.isArgsValid(sysUserDao.getRowCount(username)>0, "用户名已存在");
        String password=entity.getPassword();
        ValidUtils.isArgsValid(password==null || "".equals(password),"密码不能为空");
        ValidUtils.isArgsValid(roleIds==null || roleIds.length==0, "必须为用户分配一个角色");
        //2.保存用户自身信息
        //2.1对密码进行加密
        String salt= UUID.randomUUID().toString();//产生随机字符串,让此字符串作为一个加密盐
        //String newPwd=DigestUtils.md5DigestAsHex((password+salt).getBytes());//Spring自带
        SimpleHash sh=new SimpleHash(/*Shiro框架*/
                "MD5", /*algorithmName 算法名*/
                password,   /*原密码*/
                salt,       /*盐值*/
                1);/*hashIterations 加密次数*/
        String newPwd=sh.toHex();
        //2.2将盐值和密码存储到entity对象
        entity.setSalt(salt);
        entity.setPassword(newPwd);
        int rows=sysUserDao.insertObject(entity);
        //3.保存用户角色关系数据
        sysUserRoleDao.insertObjects(entity.getId(), roleIds);
        return rows;
    }

    @Override
    public int validById(Integer id, Integer valid) {
        ValidUtils.isArgsValid(id==null || id<1, "参数不合法，id="+id);
        int rows = sysUserDao.validById(id, valid, "admin");
        ValidUtils.isResultValid(rows==0, "记录可能已经不存在");
        return rows;
    }

    @Override
    public PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent) {
        //1.参数校验
        ValidUtils.isArgsValid(pageCurrent==null || pageCurrent<1,"页码值不正确");
        //2.查询记录数并校验
        int rowCount = sysUserDao.getRowCount(username);
        ValidUtils.isResultValid(rowCount==0, "没有找到对应的记录");
        //3.查询当前页记录
        int pageSize=10;
        int startIndex=(pageCurrent-1)*pageSize;
        List<SysUserDept> records = sysUserDao.findPageObjects(username, startIndex, pageSize);
        //4.封装查询结果
        return new PageObject<>(rowCount,records,pageCurrent,pageSize);
    }
}
