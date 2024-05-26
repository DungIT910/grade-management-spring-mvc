/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services;

import com.ttd.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
public interface UserService extends UserDetailsService {

    User getUserByUn(String username);

    User getUserById(String userId);

    boolean authUser(String username, String password);

    public boolean addOrUpdateUser(User u);

    User addUser(Map<String, String> params, MultipartFile avatar);

    public List<User> getUsersByRole(String role);

    boolean deleteUser(User user);

    boolean deleteUserById(String userId);

    boolean changeStatus(String userId);

    boolean isExistedUserId(String userId);
}
