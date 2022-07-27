package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.ArticleTag;

import java.util.List;

public interface ArticleTagDao {
    ArticleTag getArticleTagById(int atid);

    List<ArticleTag> getAllArticleTags();

    ArticleTag addArtricleTag(ArticleTag articleTag);

    void updateArticleTag(ArticleTag articleTag);

    void deleteArticleTagById(int atid);
}
