package org.sang.bean;

import java.sql.Timestamp;

/**
 * Copyright (C), 2020-2020, 滁州学院
 * FileName: Notifications
 * Author:   Jun
 * Date:     2020/3/20 23:14
 * Description: 公告Bean
 */
public class Notifications {
    Long id;
    String notification;
    Timestamp date;
    String upracticecompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getUpracticecompany() {
        return upracticecompany;
    }

    public void setUpracticecompany(String upracticecompany) {
        this.upracticecompany = upracticecompany;
    }
}
