/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;

/**
 *
 * @author DELL
 */
@Data
public class PaginationResult<T> {
    private List<T> data;
    private int totalPage;
    private int page;
   
}
