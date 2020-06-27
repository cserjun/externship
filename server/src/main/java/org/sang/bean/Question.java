package org.sang.bean;

import java.sql.Timestamp;

/**
 * Copyright (C), 2020-2020, 滁州学院
 * FileName: Question
 * Author:   Jun
 * Date:     2020/3/13 0:10
 * Description: 问题汇总
 */
public class Question {
    private Long id;
    private String question;
    private Long uid;
    private Timestamp date;
    private String nickname;
    private String upracticecompany;

    public String getUpracticecompany() {
        return upracticecompany;
    }

    public void setUpracticecompany(String upracticecompany) {
        this.upracticecompany = upracticecompany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
