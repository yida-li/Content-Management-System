/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.Article;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author erres
 */
@Repository
public class ArticleDaoDB implements ArticleDao {

    @Autowired
    JdbcTemplate jdbc;

//    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public final class ArticleMapper implements RowMapper<Article> {

        @Override
        public Article mapRow(ResultSet rs, int index) throws SQLException {
            Article article = new Article();
            article.setAid(rs.getInt("aid"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setDisplay(rs.getInt("display"));
            
            if (rs.getTimestamp("updateTime") != null) {
                article.setUpdateTime(rs.getTimestamp("updateTime").toLocalDateTime());
            }            
            article.setCreateTime(rs.getTimestamp("createTime").toLocalDateTime());    
            if (rs.getTimestamp("expireTime") != null) {
                article.setExpireTime(rs.getTimestamp("expireTime").toLocalDateTime());
            }

            return article;
        }
    }

    @Override
    public Article getArticleById(int aid) {
        final String GET_ARTICLE_BY_ID = "select * from article where aid=?";
        try {
            return jdbc.queryForObject(GET_ARTICLE_BY_ID, new ArticleMapper(), aid);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Article> getAllArticles() {
        final String GET_ALL_ARTICLE = "select * from article where display=1";
        try {
            return jdbc.query(GET_ALL_ARTICLE, new ArticleMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Article> getAllDraft() {
        final String GET_ALL_ARTICLE = "select * from article where display=0";
        try {
            return jdbc.query(GET_ALL_ARTICLE, new ArticleMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    
    @Override
    @Transactional
    public Article addArtricle(Article article) {
        final String ADD_ARTICLE = "insert into article(title,content,display,createTime,updateTime,expireTime) values(?,?,?,?,?,?)";
        jdbc.update(ADD_ARTICLE, article.getTitle(),
                article.getContent(),
                article.getDisplay(),
                article.getCreateTime(),
                article.getUpdateTime(),
                article.getExpireTime());
        article.setAid(getLastIncrementIndex());
        return article;
    }

    private int getLastIncrementIndex() {
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX, Integer.class);
    }

    @Override
    public void updateArticle(Article article) {
        final String UPDATE_ARTICLE = "update article set title=?,content=?,display=?,createTime=?,updateTime=?,expireTime=? where aid=?";
        jdbc.update(UPDATE_ARTICLE, article.getTitle(),
                article.getContent(),
                article.getDisplay(),
                article.getCreateTime(),
                article.getUpdateTime(),
                article.getExpireTime(),
                article.getAid());
    }
    
    
    @Override
    public void approveArticle(Article article) {
        final String UPDATE_ARTICLE = "update article set display= 1 where aid=?";
        jdbc.update(UPDATE_ARTICLE,
                article.getAid());
    }
    
    

    @Override
    public void deleteArticleById(int aid) {
        final String DELETE_ARTICLE = "delete from article where aid=?";
        jdbc.update(DELETE_ARTICLE, aid);

    }
}
