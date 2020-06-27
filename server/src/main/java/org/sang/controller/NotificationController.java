package org.sang.controller;

import org.sang.bean.Notifications;
import org.sang.bean.RespBean;
import org.sang.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Copyright (C), 2020-2020, 滁州学院
 * FileName: NotificationController
 * Author:   Jun
 * Date:     2020/3/21 13:30
 * Description: 公告controller
 */
@RestController
public class NotificationController {

  private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

  @Autowired
    NotificationService notificationService;

  @RequestMapping(value = "/notificationsList" ,method = RequestMethod.GET)
  public List<Notifications> notificationsList(){
      return notificationService.notificationsList();
  }

  @RequestMapping(value = "/notification/{ids}", method = RequestMethod.DELETE)
  public RespBean deleteById(@PathVariable Long ids) {
    int result =notificationService.deleteNotificationById(ids) ;
    if (result==1) {
      return new RespBean("success", "删除成功!");
    }
    return new RespBean("error", "删除失败!");
  }

}
