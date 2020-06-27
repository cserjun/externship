package org.sang.service;

import org.sang.bean.Article;
import org.sang.mapper.ArticleMapper;
import org.sang.mapper.CategoryMapper;
import org.sang.mapper.TagsMapper;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagsMapper tagsMapper;
    @Autowired
    CategoryMapper categoryMapper;


    public int addNewArticle(Article article) {
        //处理文章摘要
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            //直接截取
            String stripHtml = stripHtml(article.getHtmlContent());
            article.setSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
            article.setUpracticecompany(Util.getCurrentUser().getPracticecompany());
        }
        if (article.getId() == -1) {
            //添加操作
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            article.setEditTime(timestamp);
            //设置当前用户
            article.setUid(Util.getCurrentUser().getId());
            int i = articleMapper.addNewArticle(article);
            //打标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            //更新
            article.setEditTime(new Timestamp(System.currentTimeMillis()));
            int i = articleMapper.updateArticle(article);
            //修改标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        }
    }

    private int addTagsToArticle(String[] dynamicTags, Long aid) {
        //1.删除该文章目前所有的标签
        tagsMapper.deleteTagsByAid(aid);
        //2.将上传上来的标签全部存入数据库
        tagsMapper.saveTags(dynamicTags);
        //3.查询这些标签的id
        List<Long> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);
        //4.重新给文章设置标签
        int i = tagsMapper.saveTags2ArticleTags(tIds, aid);
        return i == dynamicTags.length ? i : -1;
    }

    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }
//    @Cacheable(value = {"articlea"}, key = "#root.targetClass")
    public List<Article> getArticleByState(Integer state, Integer page, Integer count,String keywords,String keyname,String keycat) {
        int start = (page - 1) * count;
        Long uid = Util.getCurrentUser().getId();
        return articleMapper.getArticleByState(state, start, count, uid,keywords,keyname,keycat);
    }
//    @Cacheable(value = {"articlec"},key = "#root.targetClass")
    public List<Article> getArticleByCompany(Integer state, Integer page, Integer count,String keywords,String keyname,String keycat) {
        int start = (page - 1) * count;
        Long uid = Util.getCurrentUser().getId();
       // String upracticecompany= Util.getCurrentUser().getPracticecompany();
        return articleMapper.getArticleByCompany(state, start, count,uid,keywords,keyname,keycat,Util.getCurrentUser().getPracticecompany());
    }
    public List<Article> getArticleByStateByAdmin(Integer page, Integer count,String keywords) {
        int start = (page - 1) * count;
        return articleMapper.getArticleByStateByAdmin(start, count,keywords);
    }

    public int getArticleCountByState(Integer state, Long uid,String keywords) {
        return articleMapper.getArticleCountByState(state, uid,keywords);
    }

    public int getArticleCountByStatePra(Integer state,String upracticecompany,String keywords) {
        return articleMapper.getArticleCountByStatePra(state,upracticecompany,keywords);
    }
    public int updateArticleState(Long[] aids, Integer state) {
        if (state == 2) {
            return articleMapper.deleteArticleById(aids);
        } else {
            return articleMapper.updateArticleState(aids, 2);//放入到回收站中
        }
    }
//    @Cacheable(value = {"articleid"}, keyGenerator = "myKeyGenerator" ,unless = "#result.state==0||#result.state==2")
    public Article getArticleById(Long aid) {
        Article article = articleMapper.getArticleById(aid);
        articleMapper.pvIncrement(aid);
        return article;

    }

    public void pvStatisticsPerDay() {
        articleMapper.pvStatisticsPerDay();
    }

    /**
     * 获取最近七天的日期
     * @return
     */
    public List<String> getCategories() {
        return articleMapper.getCategories();
    }

    /**
     * 获取最近七天的数据
     * @return
     */
    public List<Integer> getDataStatistics() {
        return articleMapper.getDataStatistics();
    }

    public int getTechCounts(){
        return articleMapper.getArticleCounts(categoryMapper.getTechCatenameid(),Util.getCurrentUser().getPracticecompany());

    }
    public int getPhyCounts(){
        return articleMapper.getArticleCounts(categoryMapper.getPhyCatenameid(),Util.getCurrentUser().getPracticecompany());

    }
    public int getEvalCounts(){
        return articleMapper.getArticleCounts(categoryMapper.getEvalCatenameid(),Util.getCurrentUser().getPracticecompany());

    }
//    Integer[]
    public List<Integer[]> getDataArticles(){
       // ArrayList<Integer> as=new ArrayList<Integer>();
        List<Integer[]> list =new ArrayList<>();
       Integer[] numT=new Integer[8];
        Integer[] numP=new Integer[8];
        Integer[] numE=new Integer[8];
//        String[] weeks=new String[8];
//        weeks[0]="第一周";
        String weeks[]={"第一周","第二周","第三周","第四周","第五周","第六周","第七周","第八周"};
        for(int i=0;i<weeks.length;i++){
          numT[i]=articleMapper.getDataArticlesT(weeks[i],Util.getCurrentUser().getPracticecompany());
          numP[i]=articleMapper.getDataArticlesP(weeks[i],Util.getCurrentUser().getPracticecompany());
          numE[i]=articleMapper.getDataArticlesE(weeks[i],Util.getCurrentUser().getPracticecompany());
//            list.add(articleMapper.getDataArticlesT(weeks[i]));
//            list.add(articleMapper.getDataArticlesP(weeks[i]));
//            list.add(articleMapper.getDataArticlesE(weeks[i]));
        }
        list.add(numT);
        list.add(numP);
        list.add(numE);
        return list;
    }

}
