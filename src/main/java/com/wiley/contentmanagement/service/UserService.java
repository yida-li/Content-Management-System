/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.model.User;
import java.util.List;

/**
 *
 * @author erres
 */
public interface UserService {

    User getUserById(int uid);

    List<User> getAllUsers();

    User addUser(User user);

    void updateUser(User user);

    void deleteUserById(int uid);
}
