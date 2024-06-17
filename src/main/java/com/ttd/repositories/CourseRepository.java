/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface CourseRepository {
    Course getCourseById(int courseId);
    PaginationResult<Course> getCourses(Map<String, String> params);
    int countCourses(Map<String, String> params);
    boolean addOrUpdateCourse(Course subject);
    boolean deleteCourse(int id);
}
