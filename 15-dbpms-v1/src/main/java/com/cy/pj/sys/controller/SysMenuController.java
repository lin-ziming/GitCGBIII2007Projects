package com.cy.pj.sys.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("/menu/doUpdateObject")
    public JsonResult doUpdateObject(SysMenu entity){
        sysMenuService.updateObject(entity);
        return new JsonResult("update ok");
    }

    @PostMapping("/menu/doSaveObject")
    public JsonResult doSaveOject(SysMenu entity){
        sysMenuService.saveObject(entity);
        return new JsonResult("save ok");
    }

    @RequestMapping("/menu/doFindZtreeMenuNodes")
    public JsonResult doFindZtreeMenuNodes(){
        List<Node> ztreeMenuNodes = sysMenuService.findZtreeMenuNodes();
        return new JsonResult(ztreeMenuNodes);
    }

    @RequestMapping("/menu/doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        sysMenuService.deleteObject(id);
        return new JsonResult("delete ok");
    }

    @GetMapping("/menu/doFindObjects")
    public JsonResult doFindObjects(){
        return new JsonResult(sysMenuService.findObjects());
    }
}
