package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public int deleteObjects(Integer... ids) {
        //1.参数校验
        if(ids==null || ids.length==0)
            throw new IllegalArgumentException("必须提供正确的id值");
        //2.基于id删除日志
        int rows=sysLogDao.deleteObjects(ids);
        //3.校验结果并返回
        if (rows==0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }

    @RequiredLog
    @Override
    public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
        //1.参数校验
        if(pageCurrent==null||pageCurrent<1) throw new IllegalArgumentException("当前页码值不正确");
        //2.查询总记录数并校验
        int rowCount=sysLogDao.getRowCount(username);
        if(rowCount==0) throw new RuntimeException("没有找到对应的记录");
        //3.查询当前的记录
        int pageSize=5;
        int startIndex=(pageCurrent-1)*pageSize;
        List<SysLog> records = sysLogDao.findPageObjects(username, startIndex, pageSize);
        //4.封装结果并返回
//        PageObject<SysLog> po=new PageObject<>();
//        po.setRowCount(rowCount);
//        po.setRecords(records);
//        po.setPageCurrent(pageCurrent);
//        po.setPageSize(pageSize);
//        int pageCount=rowCount/pageSize;
//        if(rowCount%pageSize!=0) pageCount++;
//        po.setPageCount(pageCount);
//        return po;
        return new PageObject<>(rowCount, records, pageCurrent, pageSize);
    }
}
