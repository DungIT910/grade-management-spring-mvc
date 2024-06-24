/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services.impl;

import com.ttd.pojo.Maingrade;
import com.ttd.repositories.MaingradeRepository;
import com.ttd.services.MaingradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class MaingradeServiceImpl implements MaingradeService {

    @Autowired
    private MaingradeRepository maingradeRepository;

    @Override
    public Maingrade getMaingradeById(String userId, int courseId) {
        return this.maingradeRepository.getMaingradeById(userId, courseId);
    }

}
