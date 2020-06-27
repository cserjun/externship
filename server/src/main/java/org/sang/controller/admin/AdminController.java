package org.sang.controller.admin;

import org.sang.bean.Article;
import org.sang.bean.Notifications;
import org.sang.bean.RespBean;
import org.sang.service.ArticleService;
import org.sang.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ArticleService articleService;

    @Autowired
    NotificationService notificationService;



//    @RequestMapping(value = "/article/all", method = RequestMethod.GET)
//    public Map<String, Object> getArticleByStateByAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count,
//                                                        String keywords,String keyname,String keycat) {
//        List<Article> articles = articleService.getArticleByState(-2, page, count, keywords,keyname,keycat);
//        Map<String, Object> map = new HashMap<>();
//        map.put("articles", articles);
//        map.put("totalCount", articleService.getArticleCountByState(1, null, keywords));
//        return map;
//    }

    @RequestMapping(value = "/article/dustbin", method = RequestMethod.PUT)
    public RespBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/addNotification", method = RequestMethod.POST)
    public RespBean addNewNotification(Notifications notifications) {
        int result = notificationService.addNewNotification(notifications);
        if (result == 1) {
            return new RespBean("success", notifications.getId() + "");
        } else {
            return new RespBean("error", "公告发表失败!");
        }
    }
}