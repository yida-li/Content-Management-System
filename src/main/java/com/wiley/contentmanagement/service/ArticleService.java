/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.ArticleTag;
import java.util.List;

/**
 *
 * @author erres
 */
public interface ArticleService {

    Article getArticleById(int aid);

    List<Article> getAllArticles();

    Article addArtricle(Article article);

    void updateArticle(Article article);

    void deleteArticleById(int aid);

    public void addTag(ArticleTag articleTag);

    List<Article> getAllDisplayArticles();

}
