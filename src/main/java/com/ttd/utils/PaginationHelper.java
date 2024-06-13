/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.utils;

import com.ttd.dto.PaginationResult;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author DELL
 */
public class PaginationHelper {
    public static <T> PaginationResult<T> paginate(Query query, Map<String, String> params, int count) {
        int page = params.containsKey("page") ? Integer.parseInt(params.get("page")) : 1;
        int limit = params.containsKey("limit") ? Integer.parseInt(params.get("limit")) : count;
        int totalPage = (int) Math.ceil((double) count / limit);
        
        List<T> data = query
            .setFirstResult((page - 1) * limit)
            .setMaxResults(limit)
            .getResultList();
        
        PaginationResult<T> result = new PaginationResult<>();
        result.setData(data);
        result.setTotalPage(totalPage);
        result.setPage(page);
        
        return result;
    }
}
