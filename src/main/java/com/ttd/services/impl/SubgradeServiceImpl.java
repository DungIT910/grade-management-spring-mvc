/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services.impl;

import com.ttd.pojo.Subgrade;
import com.ttd.repositories.SubgradeRepository;
import com.ttd.services.SubgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class SubgradeServiceImpl implements SubgradeService {

    @Autowired
    private SubgradeRepository subgradeRepository;

    @Override
    public Subgrade getSubgradeById(int subcolId, String userId) {
        return this.subgradeRepository.getSubgradeById(subcolId, userId);
    }

    @Override
    public boolean isExistSubgrade(int subcolId, String userId) {
        return this.subgradeRepository.isExistSubgrade(subcolId, userId);
    }

}
