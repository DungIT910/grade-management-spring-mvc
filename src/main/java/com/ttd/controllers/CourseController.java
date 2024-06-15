///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.ttd.controllers;
//
//import com.ttd.pojo.Course;
//import com.ttd.pojo.User;
//import com.ttd.services.CourseService;
//import com.ttd.services.UserService;
//import java.security.Principal;
//import java.util.Map;
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// *
// * @author admin
// */
//@Controller
//@RequestMapping("/admin")
//public class CourseController {
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("")
//    public String admin(Model model) {
//        return "redirect:/admin/courses/";
//    }
//
//    @GetMapping("/courses")
//    public String create(Model model) {
//        Course c = new Course();
//        model.addAttribute("course", c);
//        return "crudCourse";
//    }
//    @PostMapping("/courses")
//    public String addOrUpdate(@ModelAttribute("course") @Valid User u, Model model,
//            BindingResult rs) {
//        User a = u;
//        if (!rs.hasErrors()) {
////            if (UserService.addOrUpdateUser(u) == true) {
////                return "redirect:/";
////            }
//        }
//        return "crudCourse";
//    }
//
//    @GetMapping("/courses/")
//    public String list(Model model) {
//        model.addAttribute("courses", this.userService.getUsersByRole(User.ROLE_LECTURER));
//        return "courses";
//    }
//    @GetMapping("/courses/{id}")
//    public String detail(Model model, @PathVariable(value = "id") String userId) {
//        User user = this.userService.getUserById(userId);
//        user.setDeletedId(userId);
//        model.addAttribute("course", user);
//        return "crudCourse";
//    }
//
//}
