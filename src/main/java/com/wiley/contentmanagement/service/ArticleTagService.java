package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.Tag;

import java.util.List;

public interface ArticleTagService {
    List<Tag> getArticleTagByAid(int aid);

    List<Article> getArticlesByTid(int tid);

    void deleteArticleTagByAid(int aid);

    void deleteATByAidAndTid(int aid,int tid);

    public int updateArticleTag(int aid,List<Integer> tid);
}
