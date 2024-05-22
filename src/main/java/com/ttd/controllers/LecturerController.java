/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.controllers;

import com.ttd.pojo.User;
import com.ttd.services.UserService;
import java.security.Principal;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/admin")
public class LecturerController {
    private final String ROLE_LECTURER = "ROLE_LECTURER";
    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public String admin(Model model) {
        return "redirect:/admin/lecturers";
    }
    @GetMapping("/lecturers/")
    public String list(Model model) {
        model.addAttribute("lecturers", this.userService.getUsersByRole(ROLE_LECTURER));
        return "Lecturers";
    }
    
    @GetMapping("/lecturers/new")
    public String create(Model model, Principal p) {
        User u = new User();
        u.setUserRole(ROLE_LECTURER);
        model.addAttribute("lecturer", u);
        return "Lecturers";
    }
    @PostMapping("/lecturers/new")
    public String add(Model model, Principal p) {
//        if (!rs.hasErrors())
//            if (proService.addOrUpdateProduct(p) == true)
//                return "redirect:/";
        
        return "Lecturers";
    }
}
