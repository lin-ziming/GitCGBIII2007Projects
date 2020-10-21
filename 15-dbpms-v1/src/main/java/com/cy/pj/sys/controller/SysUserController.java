package com.cy.pj.sys.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.service.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/user/doUpdatePassword")
    public JsonResult doUpdatePassword(String pwd,String newPwd,String cfgPwd){
        sysUserService.updatePassword(pwd, newPwd, cfgPwd);
        return new JsonResult("update ok");
    }

    @PostMapping("/user/doUpdateObject")
    public JsonResult doUpdateObject(SysUser entity,Integer[] roleIds){
        sysUserService.updateObject(entity, roleIds);
        return new JsonResult("update ok");
    }

    @GetMapping("/user/doFindObjectById")
    public JsonResult doFindObjectById(Integer id){
        Map<String,Object> map=sysUserService.findObjectById(id);
        return new JsonResult(map);
    }

    @PostMapping("/user/doSaveObject")
    public JsonResult doSaveObject(SysUser entity, Integer[] roleIds){
        sysUserService.saveObject(entity, roleIds);
        return new JsonResult("save ok");
    }

    @RequestMapping("/user/doValidById")
    public JsonResult doValidById(Integer id,Integer valid){
        sysUserService.validById(id, valid);
        return new JsonResult("update ok");
    }

    @GetMapping("/user/doFindPageObjects")
    public JsonResult doFindPageObjects(String username,Integer pageCurrent){
        return new JsonResult(sysUserService.findPageObjects(username, pageCurrent));
    }
}
