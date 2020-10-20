package com.cy.pj.sys.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/role/doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        sysRoleService.deleteObject(id);
        return new JsonResult("delete ok");
    }

    @PostMapping("/role/doSaveObject")
    public JsonResult doSaveObject(SysRole entity,Integer[] menuIds){
        sysRoleService.saveObject(entity, menuIds);
        return new JsonResult("save ok");
    }

    @GetMapping("/role/doFindPageObjects")
    public JsonResult doFindPageObjects(String name,Integer pageCurrent){
        return new JsonResult(sysRoleService.findPageObjects(name, pageCurrent));
    }
}
