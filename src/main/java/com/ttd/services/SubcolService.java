/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services;

import com.ttd.pojo.Subcol;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface SubcolService {

    List<Subcol> getSubcols(Map<String, String> params);

    int countSubcols(Map<String, String> params);

    Subcol getSubcol(Map<String, String> params);

    boolean addOrUpdateSubcol(Subcol subcol);

    boolean deleteSubcol(Map<String, String> params);
}
