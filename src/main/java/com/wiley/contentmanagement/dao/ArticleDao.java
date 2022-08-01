/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.Article;
import java.util.List;

/**
 *
 * @author erres
 */
public interface ArticleDao {

    Article getArticleById(int aid);

    List<Article> getAllArticles();

    Article addArtricle(Article article);
    List<Article> getAllDraft();
    void updateArticle(Article article);
    void approveArticle(Article article);
    void deleteArticleById(int aid);

}
