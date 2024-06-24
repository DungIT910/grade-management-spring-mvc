/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Post;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface PostRepository {

    PaginationResult<Post> getPosts(Map<String, String> params);

    int countPosts(Integer parentId, int postId);

    Post addOrUpdatePost(Post post);

    Post getPostById(int postId);

    boolean deletePost(int id);
}
