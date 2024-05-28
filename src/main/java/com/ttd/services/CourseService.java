/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services;

import com.ttd.pojo.Course;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
public interface CourseService {
    public Course getCourseById(int courseId);
    public List<Course> getCourseByLecId(String userId);
    public List<Course> getCourseByStuId(String userId);
}
