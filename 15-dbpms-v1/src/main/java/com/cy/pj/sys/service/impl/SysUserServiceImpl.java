package com.cy.pj.sys.service.impl;

import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.common.util.ValidUtils;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.pojo.SysUserDept;
import com.cy.pj.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent) {
        //1.参数校验
        ValidUtils.isArgsValid(pageCurrent==null || pageCurrent<1,"页码值不正确");
        //2.查询记录数并校验
        int rowCount = sysUserDao.getRowCount(username);
        ValidUtils.isResultValid(rowCount==0, "没有找到对应的记录");
        //3.查询当前页记录
        int pageSize=5;
        int startIndex=(pageCurrent-1)*pageSize;
        List<SysUserDept> records = sysUserDao.findPageObjects(username, startIndex, pageSize);
        //4.封装查询结果
        return new PageObject<>(rowCount,records,pageCurrent,pageSize);
    }
}
