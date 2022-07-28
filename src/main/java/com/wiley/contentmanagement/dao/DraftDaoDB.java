package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.Draft;
import com.wiley.contentmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DraftDaoDB implements DraftDao{
    @Autowired
    JdbcTemplate jdbc;

    public final class DraftMapper implements RowMapper<Draft>{

        @Override
        public Draft mapRow(ResultSet rs, int index) throws SQLException {
            Draft draft = new Draft();
            draft.setDid(rs.getInt("did"));
            draft.setUser(new User(
                    rs.getInt("uid"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getInt("role")
                    ));
            draft.setAid(rs.getInt("aid"));
            draft.setTitle(rs.getString("title"));
            draft.setContent(rs.getString("content"));
            draft.setCreateTime(rs.getTimestamp("createTime").toLocalDateTime());
            draft.setUpdateTime(rs.getTimestamp("updateTime").toLocalDateTime());
            draft.setState(rs.getInt("state"));
            return draft;
        }
    }

    @Override
    public Draft getDraftById(int did) {
        final String GET_DRAFT_BY_ID = "select * " +
                                        "from draft d" +
                                        "join user u" +
                                        "on d.`uid` = u.`uid`" +
                                        "where did =?";
        try{
            return jdbc.queryForObject(GET_DRAFT_BY_ID,new DraftMapper(),did);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public List<Draft> getAllDrafts() {
        final String GET_ALL_DRAFTS = "select * " +
                "from draft d" +
                "join user u" +
                "on d.`uid` = u.`uid`";
        try{
            return jdbc.query(GET_ALL_DRAFTS,new DraftMapper());
        }catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public Draft addDraft(Draft draft) {
        final String ADD_DRAFT = "insert into draft(uid,aid,title,content,createTime,updateTime,state) values(?,?,?,?,?,?,?)";
        try{
            jdbc.update(ADD_DRAFT,draft.getUser().getUid(),
                    draft.getAid(),
                    draft.getTitle(),
                    draft.getContent(),
                    draft.getCreateTime(),
                    draft.getUpdateTime(),
                    draft.getState());
            draft.setDid(getLastIncrementIndex());
            return draft;
        }catch (DataIntegrityViolationException e) {
            return null;
        }

    }

    private int getLastIncrementIndex() {
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX, Integer.class);
    }

    @Override
    public void updateDraft(Draft draft) {
        final String UPDATE_DRAFT = "update draft set uid=?,aid=?,title=?,content=?,createTime=?,updateTime=?,state=? where did=?";
        try{
            jdbc.update(UPDATE_DRAFT,draft.getUser().getUid(),
                    draft.getAid(),
                    draft.getTitle(),
                    draft.getContent(),
                    draft.getCreateTime(),
                    draft.getUpdateTime(),
                    draft.getState(),
                    draft.getDid());
        }catch (DataIntegrityViolationException e) {
        }

    }

    @Override
    public void deleteDraftById(int did) {
        final String DELETE_DRAFT = "delete from draft where did=?";
        try{
            jdbc.update(DELETE_DRAFT,did);
        }catch (DataIntegrityViolationException e) {
        }

    }
}
