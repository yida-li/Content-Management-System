/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.User;

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
public class UserDaoDB implements UserDao {

    @Autowired
    private JdbcTemplate jdbc;

    public final class UserMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setUid(rs.getInt("uid"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getInt("role"));
            return user;
        }
    }

    @Override
    public User getUserById(int uid) {
        final String GET_USER_BY_ID = "select * from user where uid = ?";
        try{
            return jdbc.queryForObject(GET_USER_BY_ID,new UserMapper(),uid);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        final String GET_ALL_USERS = "select * from user";
        try{
            return jdbc.query(GET_ALL_USERS,new UserMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        final String ADD_USER = "insert into user(name,password,role) values(?,?,?)";
        jdbc.update(ADD_USER,user.getName(),
                            user.getPassword(),
                            user.getRole());
        user.setUid(getLastIncrementIndex());
        return user;
    }

    private int getLastIncrementIndex(){
        final String GET_LAST_INCREMENT_INDEX = "select @@identity";
        return jdbc.queryForObject(GET_LAST_INCREMENT_INDEX,Integer.class);
    }

    @Override
    public void updateUser(User user) {
        final String UPDATE_USER = "update user set name=?, password=?, role=? where uid=?";
        jdbc.update(UPDATE_USER,user.getName(),
                                user.getPassword(),
                                user.getRole(),
                                user.getUid());
    }

    @Override
    public void deleteUserById(int uid) {
        final String DELETE_USER = "delete from user where uid=?";
        jdbc.update(DELETE_USER,uid);
    }

}
