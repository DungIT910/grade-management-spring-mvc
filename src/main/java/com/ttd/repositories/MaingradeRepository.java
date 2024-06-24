/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Maingrade;
import com.ttd.pojo.Post;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface MaingradeRepository {

    Maingrade getMaingradeById(String userId, int courseId);
}
