/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author erres
 */
@Repository
public class TagDaoDB implements TagDao {

    @Autowired
    JdbcTemplate jdbc;

    public final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int index) throws SQLException {
            Tag tag = new Tag();
            tag.setTid(rs.getInt("tid"));
            tag.setName(rs.getString("name"));
            return tag;
        }
    }

    @Override
    public Tag getTagById(int tid) {
        final String GET_TAG_BY_ID = "select * from tag where tid=?";
        try {
            return jdbc.queryForObject(GET_TAG_BY_ID, new TagMapper(), tid);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Tag> getAllTags() {
        final String GET_ALL_TAGS = "select * from tag";
        try{
            return jdbc.query(GET_ALL_TAGS,new TagMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Tag addTag(Tag tag) {
        final String ADD_TAG = "insert into tag(name) values(?)";
        jdbc.update(ADD_TAG,tag.getName());
        tag.setTid(getLastIncrementIndex());
        return tag;
    }

    private int getLastIncrementIndex() {
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX, Integer.class);
    }

    @Override
    public void updateTag(Tag tag) {
        final String UPDATE_TAG = "update tag set name=?";
        jdbc.update(UPDATE_TAG,tag.getName());
    }

    @Override
    public void deleteTagById(int tid) {
        final String DELETE_TAG = "delete from tag where tid=?";
        jdbc.update(DELETE_TAG,tid);
    }
}
