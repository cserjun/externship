package org.sang.service;

import org.sang.bean.Notifications;
import org.sang.mapper.NotificationsMapper;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Copyright (C), 2020-2020, 滁州学院
 * FileName: NotificationService
 * Author:   Jun
 * Date:     2020/3/21 9:32
 * Description: 公告服务
 */
@Service
@Transactional
public class NotificationService {

    @Autowired
    NotificationsMapper notificationsMapper;

   public int addNewNotification(Notifications notifications){
       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       notifications.setDate(timestamp);
       notifications.setUpracticecompany(Util.getCurrentUser().getPracticecompany());
       return notificationsMapper.addNewNotification(notifications);
   }
   public List<Notifications> notificationsList(){
     return   notificationsMapper.notificationsList(Util.getCurrentUser().getPracticecompany());
   }

    public int deleteNotificationById( Long ids){
       return notificationsMapper.deleteNotificationById(ids);
    }
}
