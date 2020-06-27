package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.Notifications;

import java.util.List;


@Mapper
public interface NotificationsMapper {

    int addNewNotification(Notifications notifications);

   List<Notifications> notificationsList(@Param("upracticecompany") String upracticecompany);

    int deleteNotificationById(@Param("aid") Long aid);

    int findNotificationNumber();

}
