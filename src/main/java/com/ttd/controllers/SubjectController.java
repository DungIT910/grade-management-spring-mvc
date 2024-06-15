/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.controllers;

import com.ttd.pojo.Subject;
import com.ttd.services.SubjectService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private Environment env;

    @GetMapping("/subjects")
    public String create(Model model) {
        Subject s = new Subject();
        model.addAttribute("subject", s);
        return "crudSubject";
    }
    @PostMapping("/subjects")
    public String addOrUpdate(@ModelAttribute("subject") @Valid Subject subject, Model model,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (subjectService.addOrUpdateSubject(subject) == true) {
                return "redirect:/admin/subjects/";
            }
        }
        return "crudSubject";
    }

    @GetMapping("/subjects/")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("subjects", this.subjectService.getSubjects(params));
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.subjectService.countSubjects();
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        
        return "subjects";
    }
    @GetMapping("/subjects/{id}")
    public String detail(Model model, @PathVariable(value = "id") int subjectId) {
        Subject subject = this.subjectService.getSubjectById(subjectId);
        model.addAttribute("subject", subject);
        return "crudSubject";
    }

}
