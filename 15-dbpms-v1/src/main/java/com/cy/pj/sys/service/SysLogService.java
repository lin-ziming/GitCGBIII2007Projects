package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.pojo.SysLog;

public interface SysLogService {
    void saveObject(SysLog entity);
    int deleteObjects(Integer... ids);
    PageObject<SysLog> findPageObjects(String username,Integer pageCurrent);
}
