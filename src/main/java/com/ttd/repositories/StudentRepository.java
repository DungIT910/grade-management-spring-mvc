/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface StudentRepository {
    PaginationResult<User> getStudentsByCourseId(int courseId, Map<String, String> params); 
    int countStudentsByCourseId(int courseId);
    boolean removeStudentFromCourse(int courseId, String studentId);
    boolean addStudentToCourse(int courseId, String studentId);
}
