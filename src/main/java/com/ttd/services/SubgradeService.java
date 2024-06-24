/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services;

import com.ttd.pojo.Subgrade;

/**
 *
 * @author DELL
 */
public interface SubgradeService {
    Subgrade getSubgradeById(int subcolId, String userId);
     boolean isExistSubgrade(int subcolId, String userId);
}
