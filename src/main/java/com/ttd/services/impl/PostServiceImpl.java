/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.services.impl;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Post;
import com.ttd.repositories.PostRepository;
import com.ttd.services.PostService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PaginationResult<Post> getPosts(Map<String, String> params) {
        return this.postRepository.getPosts(params);
    }

    @Override
    public int countPostsByParentId(Integer parentId, int forumId) {
        return this.postRepository.countPosts(parentId, forumId);
    }

    @Override
    public Post addOrUpdatePost(Post post) {
        return this.postRepository.addOrUpdatePost(post);
    }

    @Override
    public Post getPostById(int postId) {
        return this.postRepository.getPostById(postId);
    }

    @Override
    public boolean deletePost(int id) {
        return this.postRepository.deletePost(id);
    }

}
