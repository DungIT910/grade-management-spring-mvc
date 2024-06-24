/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services;

import com.ttd.pojo.Maingrade;

/**
 *
 * @author DELL
 */
public interface MaingradeService {
    Maingrade getMaingradeById(String userId, int courseId);
}
