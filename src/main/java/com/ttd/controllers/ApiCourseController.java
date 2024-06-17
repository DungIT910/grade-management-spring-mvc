/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.controllers;

import com.ttd.components.JwtService;
import com.ttd.dto.GradeDetail;
import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.Subcol;
import com.ttd.pojo.User;
import com.ttd.services.CourseService;
import com.ttd.services.GradeService;
import com.ttd.services.StudentService;
import com.ttd.services.SubcolService;
import com.ttd.services.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")
public class ApiCourseController {

    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubcolService subcolService;

    @GetMapping(path = "/courses/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<PaginationResult<Course>> listCourses(Principal user, @RequestParam Map<String, String> params) {
        User u = this.userService.getUserByUn(user.getName());
        params.put("userId", u.getId());
        return new ResponseEntity<>(this.courseService.getCourses(params), HttpStatus.OK);
    }

    @GetMapping(path = "/courses/{courseId}/students/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<PaginationResult<User>> listStudents(@PathVariable(value = "courseId") int courseId, @RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.studentService.getStudentsByCourseId(courseId, params), HttpStatus.OK);
    }

    @GetMapping(path = "/courses/{courseId}/grades/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<PaginationResult<GradeDetail>> listGrades(@PathVariable(value = "courseId") String courseId, @RequestParam Map<String, String> params) {
        params.put("courseId", courseId);
        return new ResponseEntity<>(this.gradeService.getGrades(params), HttpStatus.OK);
    }

    @PostMapping(path = "/courses/{courseId}/subcols/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> addSubcol(@PathVariable(value = "courseId") int courseId, @RequestBody Subcol subcol) {
        subcol.setCourseId(courseService.getCourseById(courseId));
        if (this.subcolService.addOrUpdateSubcol(subcol)) {
            return new ResponseEntity<>(subcol, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/courses/{courseId}/subcols/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "courseId") String courseId, @RequestParam Map<String, String> params) {
        if (params.containsKey("name")) {
            params.put("courseId", courseId);
            this.subcolService.deleteSubcol(params);
        }
    }
}
