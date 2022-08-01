package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.ArticleTag;
import com.wiley.contentmanagement.model.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleTagDaoDB implements ArticleTagDao {

    @Autowired
    JdbcTemplate jdbc;

    public final class ArticleTagMapper implements RowMapper<ArticleTag> {

        @Override
        public ArticleTag mapRow(ResultSet rs, int index) throws SQLException {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setAtid(rs.getInt("atid"));
            articleTag.setArticle(new Article(rs.getInt("aid"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("display"),
                    rs.getTimestamp("createTime").toLocalDateTime(),
                    rs.getTimestamp("updateTime") == null ? null : rs.getTimestamp("updateTime").toLocalDateTime(),
                    rs.getTimestamp("expireTime") == null ? null : rs.getTimestamp("expireTime").toLocalDateTime()
            ));
            articleTag.setTag(new Tag(rs.getInt("tid"),
                    rs.getString("name")));
            return articleTag;
        }
    }

    @Override
    public ArticleTag getArticleTagById(int atid) {
        final String GET_AT_BY_ID = "select * "
                + " from articletag at"
                + " join article a"
                + " on at.`aid`=a.`aid`"
                + " join tag t"
                + " on at.`tid`=t.`tid`"
                + " where atid=?";
        try {
            return jdbc.queryForObject(GET_AT_BY_ID, new ArticleTagMapper(), atid);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            return null;
        }

    }

    @Override
    public List<ArticleTag> getAllArticleTags() {
        final String GET_ALL = "select * "
                + " from articletag at"
                + " join article a"
                + " on at.`aid`=a.`aid`"
                + " join tag t"
                + " on at.`tid`=t.`tid`";
        try {
            return jdbc.query(GET_ALL, new ArticleTagMapper());
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public ArticleTag addArtricleTag(ArticleTag articleTag) {
        final String ADD_AT = "insert into articletag(aid,tid) values(?,?)";
        try {
            jdbc.update(ADD_AT,
                    articleTag.getArticle().getAid(),
                    articleTag.getTag().getTid());
            articleTag.setAtid(getLastIncrementIndex());
            return articleTag;
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    private int getLastIncrementIndex() {
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX, Integer.class);
    }

    @Override
    public void updateArticleTag(ArticleTag articleTag) {
        final String UPDATE_AT = "update articletag set aid=?,tid=? where atid=?";
        try {
            jdbc.update(UPDATE_AT, articleTag.getArticle().getAid(),
                    articleTag.getTag().getTid(),
                    articleTag.getAtid());
        } catch (DataIntegrityViolationException e) {

        }
    }

    @Override
    public void deleteArticleTagById(int atid) {
        final String DELETE_AT = "delete from articletag where atid=?";
        try {
            jdbc.update(DELETE_AT, atid);
        } catch (DataIntegrityViolationException e) {
        }
    }
}
