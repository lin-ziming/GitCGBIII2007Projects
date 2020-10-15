package com.cy.pj.activity.service;

import com.cy.pj.activity.pojo.Activity;

import java.util.List;
//引入包中的类：ctrl+shift+o

public interface ActivityService {
    List<Activity> findActivities();

    int saveActivity(Activity entity);
}
