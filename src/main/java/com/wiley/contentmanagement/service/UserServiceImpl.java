/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.dao.UserDao;
import com.wiley.contentmanagement.model.User;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author erres
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao uDao;

    @Override
    public User getUserById(int uid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> getUserByName(String name) {
        return uDao.getAllUsers()
                .stream()
                .filter(u->u.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsers() {
        return uDao.getAllUsers();
    }

    @Override
    public User addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteUserById(int uid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
