package com.cy.pj.activity.controller;

import com.cy.pj.activity.pojo.Activity;
import com.cy.pj.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/activity/")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("doSaveActivity")
    @ResponseBody
    public String doSaveActivity(Activity entity){
        activityService.saveActivity(entity);
        return "save ok";
    }

    @GetMapping("doActivityUI")
    public String doActivityUI(){
        return "activity";
    }

    @GetMapping("doFindActivities")
    @ResponseBody
    public List<Activity> doFindActivities(){
        List<Activity> list=activityService.findActivities();
        return list;
    }
}
