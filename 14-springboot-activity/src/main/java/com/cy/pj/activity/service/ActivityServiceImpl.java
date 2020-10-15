package com.cy.pj.activity.service;

import com.cy.pj.activity.dao.ActivityDao;
import com.cy.pj.activity.pojo.Activity;
import com.cy.pj.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<Activity> findActivities() {

        return activityDao.findActivity();
    }

    @Override
    public int saveActivity(Activity entity) {
//        Timer timer=new Timer();
        return activityDao.insertObject(entity);
    }
}
