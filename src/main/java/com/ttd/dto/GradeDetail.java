/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.dto;

import com.ttd.pojo.Course;
import com.ttd.pojo.User;
import java.math.BigDecimal;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author DELL
 */
@Data
public class GradeDetail {
    private User user;
    private Course course;
    private BigDecimal midtermGrade;
    private BigDecimal finalGrade;
    private Map<String, BigDecimal> subgrades;
}
