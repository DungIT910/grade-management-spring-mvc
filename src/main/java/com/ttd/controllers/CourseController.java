/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.controllers;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.Course;
import com.ttd.pojo.User;
import com.ttd.services.CourseService;
import com.ttd.services.SubjectService;
import com.ttd.services.CourseService;
import com.ttd.services.StudentService;
import com.ttd.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/admin")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private StudentService studentService;
    
    @ModelAttribute
    public void commonAttr(Model model) {
        Map<String, String> params = new HashMap<>();
        model.addAttribute("subjects", this.subjectService.getSubjects(params));
        model.addAttribute("lecturers", this.userService.getUsersByRole(User.ROLE_LECTURER));
    }

    @GetMapping("/courses")
    public String create(Model model) {
        Course c = new Course();
        model.addAttribute("course", c);
        return "crudCourse";
    }

    @PostMapping("/courses")
    public String addOrUpdate(@ModelAttribute(value = "course") @Valid Course c,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (courseService.addOrUpdateCourse(c) == true) {
                return "redirect:/admin/courses/";
            }
        }
        return "crudCourse";
    }

    @GetMapping("/courses/")
    public String list(Model model, @RequestParam Map<String, String> params) {
        PaginationResult<Course> result = this.courseService.getCourses(params);
        List<Course> listCourse = result.getData();
        model.addAttribute("courses", listCourse);

        model.addAttribute("counter", result.getTotalPage());
        
        return "courses";
    }

    @GetMapping("/courses/{id}")
    public String detail(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params) {
        Course course = this.courseService.getCourseById(id);
        PaginationResult<User> result = this.studentService.getStudentsByCourseId(id, params);
        List<User> students = result.getData();
        model.addAttribute("course", course);
        model.addAttribute("students", students);
        
        return "crudCourse";
    }
}
