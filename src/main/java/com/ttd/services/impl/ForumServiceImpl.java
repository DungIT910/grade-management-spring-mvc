/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services.impl;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Forum;
import com.ttd.repositories.ForumRepository;
import com.ttd.services.ForumService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ForumRepository forumRepo;

    @Override
    public Forum getForumById(int forumId) {
        return this.forumRepo.getForumById(forumId);
    }

    @Override
    public PaginationResult<Forum> getForums(Map<String, String> params) {
        return this.forumRepo.getForums(params);
    }

    @Override
    public boolean addOrUpdateForum(Forum forum) {
        return this.forumRepo.addOrUpdateForum(forum);
    }

    @Override
    public boolean deleteForum(int id) {
        return this.forumRepo.deleteForum(id);

    }

    @Override
    public int countForums(Map<String, String> params) {
        return this.forumRepo.countForums(params);
    }
}
