/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services.impl;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.User;
import com.ttd.repositories.CourseRepository;
import com.ttd.repositories.StudentRepository;
import com.ttd.services.CourseService;
import com.ttd.services.StudentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public PaginationResult<User> getStudentsByCourseId(int courseId, Map<String, String> params) {
        return this.studentRepository.getStudentsByCourseId(courseId, params);
    }

    @Override
    public int countStudentsByCourseId(int courseId) {
        return this.studentRepository.countStudentsByCourseId(courseId);
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, String studentID) {
        return this.studentRepository.removeStudentFromCourse(courseId, studentID);
    }

    @Override
    public boolean addStudentToCourse(int courseId, String studentId) {
        return this.studentRepository.addStudentToCourse(courseId, studentId);

    }

}
