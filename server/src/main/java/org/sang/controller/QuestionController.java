package org.sang.controller;


import org.sang.bean.Question;
import org.sang.bean.RespBean;
import org.sang.service.QuestionService;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/question")
public class QuestionController {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Question> getAllQuestion() {

        return questionService.getAllQuestion();
    }    @RequestMapping(value = "/allUid", method = RequestMethod.GET)
    public List<Question> getAllUidQuestion() {

        return questionService.getAllUidQuestion();
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteById(@PathVariable String ids) {
        boolean result =questionService.deleteQuestionByIds(ids) ;
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewQuestion(Question question) {
        int result = questionService.addQuestion(question);
        if (result == 1) {
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateCate(Question question) {
        int i = questionService.updateQuestionById(question);
        if (i == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }
    @RequestMapping(value ="/getQuestionsCounts", method=RequestMethod.GET)
    public  int getQuestionsCounts(){
        return questionService.getQuestionsCounts();
    }
}
