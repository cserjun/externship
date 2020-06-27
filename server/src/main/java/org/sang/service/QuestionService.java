package org.sang.service;


import org.sang.bean.Question;
import org.sang.mapper.QuestionMapper;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Copyright (C), 2020-2020, 滁州学院
 * FileName: QuestionService
 * Author:   Jun
 * Date:     2020/3/13 0:58
 * Description: QuestionService
 */
@Service
@Transactional
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

   public List<Question> getAllQuestion(){

       return questionMapper.getAllQuestion(Util.getCurrentUser().getPracticecompany());
    }
    public List<Question> getAllUidQuestion(){

       return questionMapper.getAllUidQuestion(Util.getCurrentUser().getId());
    }


    public boolean deleteQuestionByIds( String ids){
        String[] split;
        split = ids.split(",");
        int result = questionMapper.deleteQuestionByIds(split);
        return result == split.length;
    }


    public int updateQuestionById(Question question){
        return questionMapper.updateQuestionById(question);
    }

    public int addQuestion(Question question){
       question.setDate(new Timestamp(System.currentTimeMillis()));
      question.setUid(Util.getCurrentUser().getId());
      question.setUpracticecompany(Util.getCurrentUser().getPracticecompany());
       return questionMapper.addQuestion(question);
    }

public int getQuestionsCounts(){
       return questionMapper.getQuestionscounts(Util.getCurrentUser().getPracticecompany());
}
}
