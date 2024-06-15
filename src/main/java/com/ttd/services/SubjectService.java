/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services;

import com.ttd.pojo.Subject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface SubjectService {

    List<Subject> getSubjects(Map<String, String> params);

    int countSubjects();

    Subject getSubjectById(int id);

    boolean addOrUpdateSubject(Subject subject);

    boolean deleteSubject(int id);
}
