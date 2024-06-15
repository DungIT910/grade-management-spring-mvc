/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services.impl;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.Subject;
import com.ttd.repositories.CourseRepository;
import com.ttd.repositories.SubjectRepository;
import com.ttd.services.CourseService;
import com.ttd.services.SubjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getSubjects(Map<String, String> params) {
        return this.subjectRepository.getSubjects(params);
    }

    @Override
    public int countSubjects() {
        return this.subjectRepository.countSubjects();
    }

    @Override
    public boolean addOrUpdateSubject(Subject subject) {
        return this.subjectRepository.addOrUpdateSubject(subject);
    }

    @Override
    public boolean deleteSubject(int id) {
        return this.subjectRepository.deleteSubject(id);
    }

    @Override
    public Subject getSubjectById(int id) {
        return this.subjectRepository.getSubjectById(id);
    }
}
