/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
public interface CourseService {
    Course getCourseById(int courseId);
    PaginationResult<Course> getCoursesByUserId(String userId, Map<String, String> params);
}
