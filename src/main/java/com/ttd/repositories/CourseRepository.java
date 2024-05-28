/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories;

import com.ttd.pojo.Course;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface CourseRepository {
    Course getCourseById(int courseId);
    List<Course> getCourseByLecId(String userId);
    public List<Course> getCourseByStuId(String userId);
}
