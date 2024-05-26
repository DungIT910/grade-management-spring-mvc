/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories;

import com.ttd.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface UserRepository {
    List<User> getUsersByRole(String role);
    User getUserByUsername(String username);
    User getUserById(String userId);
    boolean authUser(String username, String password);
    boolean addOrUpdateUser(User user);
    boolean addUser(User user);
    boolean deleteUser(User user);
    boolean deleteUserById(String userId);
    boolean changeStatus(String userId);
    boolean isExistedUserId(String userId);
}
