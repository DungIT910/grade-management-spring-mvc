/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services.impl;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.repositories.CourseRepository;
import com.ttd.services.CourseService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Override
    public Course getCourseById(int courseId) {
        return this.courseRepo.getCourseById(courseId);
    }

    @Override
    public PaginationResult<Course> getCoursesByUserId(String userId, Map<String, String> params) {
        return this.courseRepo.getCoursesByUserId(userId, params);
    }
}
