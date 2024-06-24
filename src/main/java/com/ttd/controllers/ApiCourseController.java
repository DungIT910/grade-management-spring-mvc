/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.controllers;

import com.ttd.components.JwtService;
import com.ttd.dto.GradeDetail;
import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.Forum;
import com.ttd.pojo.Maingrade;
import com.ttd.pojo.Subcol;
import com.ttd.pojo.User;
import com.ttd.services.CourseService;
import com.ttd.services.ForumService;
import com.ttd.services.GradeService;
import com.ttd.services.MaingradeService;
import com.ttd.services.StudentService;
import com.ttd.services.SubcolService;
import com.ttd.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Autowired
    private ForumService forumService;
    @Autowired
    private MaingradeService maingradeService;

    // COURSE
    @GetMapping(path = "/courses/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<PaginationResult<Course>> listCourses(Principal user, @RequestParam Map<String, String> params) {
        User u = this.userService.getUserByUn(user.getName());
        params.put("userId", u.getId());
        return new ResponseEntity<>(this.courseService.getCourses(params), HttpStatus.OK);
    }
    @GetMapping(path = "/courses/{courseId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> listCourses(@PathVariable(value = "courseId") int courseId) {
        return new ResponseEntity<>(this.courseService.getCourseById(courseId), HttpStatus.OK);
    }

    // STUDENT OF COURSE
    @GetMapping(path = "/courses/{courseId}/students/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<PaginationResult<User>> listStudents(@PathVariable(value = "courseId") int courseId, @RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.studentService.getStudentsByCourseId(courseId, params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/courses/{courseId}/students/{studentId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> studentDetail(@PathVariable(value = "courseId") int courseId, @PathVariable(value = "studentId") String studentId) {
        return new ResponseEntity<>(this.gradeService.getStudentgrade(studentId, courseId), HttpStatus.OK);
    }

    @PostMapping(path = "/courses/{courseId}/students/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> addStudents(@PathVariable(value = "courseId") int courseId, @RequestParam("studentId") String studentId) {
        if (userService.isExistedUserId(studentId)) {
            return new ResponseEntity<>(this.studentService.addStudentToCourse(courseId, studentId), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/courses/{courseId}/students/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> removeStudents(@PathVariable(value = "courseId") int courseId, @PathVariable(value = "studentId") String studentId) {
        if (userService.isExistedUserId(studentId)) {
            return new ResponseEntity<>(this.studentService.removeStudentFromCourse(courseId, studentId), HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GRADE DETAILS IN COURSES
    @GetMapping(path = "/courses/{courseId}/grades/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<PaginationResult<GradeDetail>> listGrades(@PathVariable(value = "courseId") String courseId, @RequestParam Map<String, String> params) {
        params.put("courseId", courseId);
        return new ResponseEntity<>(this.gradeService.getGrades(params), HttpStatus.OK);
    }

    @PutMapping(path = "/courses/{courseId}/grades/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> updateGrades(@PathVariable(value = "courseId") int courseId, @RequestBody GradeDetail gd) {
        gd.setUser(this.userService.getUserById(gd.getUser().getId()));
        Map<String, String> params = new HashMap<>();
        return new ResponseEntity<>(gradeService.updateGrades(gd, courseId), HttpStatus.OK);

//        return new ResponseEntity<>(this.gradeService.updateGrades(gd, courseId), HttpStatus.OK);
    }

    // SUBCOLUMN
    @PostMapping(path = "/courses/{courseId}/subcols/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> addSubcol(@PathVariable(value = "courseId") int courseId, @RequestBody Subcol subcol) {
        subcol.setCourseId(courseService.getCourseById(courseId));
        Map<String, String> params = new HashMap<>();
        params.put("courseId", String.valueOf(courseId));
        if (this.subcolService.countSubcols(params) >= 3) {
            return new ResponseEntity<>("Toi da khong qua 5 cot diem", HttpStatus.BAD_REQUEST);
        } else if (this.subcolService.addOrUpdateSubcol(subcol)) {
            return new ResponseEntity<>(subcol, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Du lieu khong hop le", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/courses/{courseId}/subcols/{subcolName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable(value = "courseId") String courseId, @PathVariable(value = "subcolName") String subcolName) {
        Map<String, String> params = new HashMap<>();
        params.put("subcolName", subcolName);
        params.put("courseId", courseId);
        if (this.subcolService.getSubcol(params) != null) {
            this.subcolService.deleteSubcol(params);
            return new ResponseEntity<>("Xoa thanh cong", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Co loi", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/courses/{courseId}/subcols/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> patch(@PathVariable(value = "courseId") String courseId, @RequestBody Subcol subcol) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(subcol.getId()));
        Subcol sc = this.subcolService.getSubcol(params);
        if (courseId.equals(String.valueOf(sc.getCourseId()))) {
            this.subcolService.addOrUpdateSubcol(sc);
        }
        return new ResponseEntity<>("Du lieu khong hop le", HttpStatus.BAD_REQUEST);
    }

    // FORUM
    @GetMapping(path = "/courses/{courseId}/forums/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<PaginationResult<Forum>> listForums(@PathVariable(value = "courseId") String courseId, @RequestParam Map<String, String> params) {
        params.put("courseId", courseId);
        return new ResponseEntity<>(this.forumService.getForums(params), HttpStatus.OK);
    }

    @PostMapping(path = "/courses/{courseId}/forums/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> addForum(@PathVariable(value = "courseId") int courseId, @RequestBody Forum forum) {
        forum.setCourseId(this.courseService.getCourseById(courseId));
        if (this.forumService.addOrUpdateForum(forum)) {
            return new ResponseEntity<>(forum, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Du lieu khong hop le", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/courses/{courseId}/forums/{forumId}")
    public ResponseEntity<String> deleteForum(@PathVariable(value = "courseId") String courseId, @PathVariable(value = "forumId") int forumId) {
        Forum forum = this.forumService.getForumById(forumId);
        if (forum != null) {
            this.forumService.deleteForum(forumId);
            return new ResponseEntity<>("xoa thanh cong", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
