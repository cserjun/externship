package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.sang.bean.Question;

import java.util.List;


@Mapper
public interface QuestionMapper {
    List<Question> getAllQuestion(@Param("upracticecompany") String  upracticecompany);
    List<Question> getAllUidQuestion(Long uid);


    int deleteQuestionByIds(@Param("ids") String[] ids);

    int updateQuestionById(Question question);

    int addQuestion(Question question);

    int getQuestionscounts(@Param("upracticecompany") String upracticecompany);
}
