/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.controllers;

import com.ttd.components.JwtService;
import com.ttd.pojo.Course;
import com.ttd.pojo.User;
import com.ttd.services.CourseService;
import com.ttd.services.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")
public class ApiCourseLecController {
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtService jwtService;
    
    @GetMapping(path = "/lecturer/courses/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Course>> listCourses(Principal user) {
        User u = this.userService.getUserByUn(user.getName());
        return new ResponseEntity<>(this.courseService.getCourseByLecId(u.getId()), HttpStatus.OK);
    }
}
