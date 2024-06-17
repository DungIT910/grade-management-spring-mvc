/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services.impl;

import com.ttd.pojo.Subcol;
import com.ttd.repositories.SubcolRepository;
import com.ttd.services.SubcolService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class SubcolServiceImpl implements SubcolService {

    @Autowired
    private SubcolRepository subcolRepository;

    @Override
    public List<Subcol> getSubcols(Map<String, String> params) {
        return this.subcolRepository.getSubcols(params);
    }

    @Override
    public int countSubcols(Map<String, String> params) {
        return this.subcolRepository.countSubcols(params);
    }

    @Override
    public Subcol getSubcol(Map<String, String> params) {
        return this.subcolRepository.getSubcol(params);
    }

    @Override
    public boolean addOrUpdateSubcol(Subcol subcol) {
        return this.subcolRepository.addOrUpdateSubcol(subcol);
    }

    @Override
    public boolean deleteSubcol(Map<String, String> params) {
        return this.subcolRepository.deleteSubcol(params);

    }

}
