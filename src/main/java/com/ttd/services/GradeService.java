/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services;

import com.ttd.dto.GradeDetail;
import com.ttd.dto.PaginationResult;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface GradeService {
     PaginationResult<GradeDetail> getGrades(Map<String, String> params); 
}
