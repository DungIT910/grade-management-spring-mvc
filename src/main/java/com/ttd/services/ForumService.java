/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Forum;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface ForumService {
    Forum getForumById(int forumId);
    PaginationResult<Forum> getForums(Map<String, String> params);
    int countForums(Map<String, String> params);
    boolean addOrUpdateForum(Forum subject);
    boolean deleteForum(int id);
}
