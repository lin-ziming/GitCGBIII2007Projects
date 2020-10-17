package com.cy.pj.sys.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log/")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

//    @RequestMapping(value = "doDeleteObjects/{ids}",method = RequestMethod.DELETE)
    @DeleteMapping("doDeleteObjects/{ids}")//rest风格
    public JsonResult doDeleteObjects(@PathVariable Integer... ids){
        sysLogService.deleteObjects(ids);
        return new JsonResult("delete ok");
    }

    @GetMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String username
            ,Integer pageCurrent){
        PageObject<SysLog> pageObject = sysLogService.findPageObjects(username, pageCurrent);
        return new JsonResult(pageObject);
    }
}
