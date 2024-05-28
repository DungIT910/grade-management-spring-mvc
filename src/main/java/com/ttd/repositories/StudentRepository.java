/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories;

import com.ttd.pojo.User;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface StudentRepository {
    List<User> getUsersByCourseId(int courseId); 
}
