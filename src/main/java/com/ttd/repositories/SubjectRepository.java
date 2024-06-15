/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Subject;
import com.ttd.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface SubjectRepository {
    List<Subject> getSubjects(Map<String, String> params); 
    int countSubjects();
    Subject getSubjectById(int id);
    boolean addOrUpdateSubject(Subject subject);
    boolean deleteSubject(int id);
}
