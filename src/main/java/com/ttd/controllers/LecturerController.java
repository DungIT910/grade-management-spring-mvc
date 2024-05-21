/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.controllers;

import com.ttd.pojo.User;
import com.ttd.services.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
public class LecturerController {
    private final String ROLE_LECTURER = "ROLE_LECTURER";
    @Autowired
    private UserService userService;
    
    @GetMapping("/all-lecturers")
    public String list(Model model) {
        model.addAttribute("lecturers", this.userService.getLecturers());
        return "viewLecturers";
    }
    
//    @GetMapping("/lecturers")
//    public String create(Model model, Principal p) {
//        User u = new User();
//        u.setUserRole(ROLE_LECTURER);
//        model.addAttribute("user", u);
//        return "addLecturers";
//    }
//    @PostMapping("/lecturers") 
//    public String add() {
//        return null;
//    }
}
