/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.controllers;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.Forum;
import com.ttd.pojo.Post;
import com.ttd.pojo.User;
import com.ttd.services.ForumService;
import com.ttd.services.PostService;
import com.ttd.services.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")
public class ApiForumController {

    @Autowired
    private PostService postService;
    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/forums/{forumId}/posts/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> listPosts(@PathVariable(value = "forumId") int forumId, @RequestParam Map<String, String> params) {
        params.put("forumId", String.valueOf(forumId));
        return new ResponseEntity<>(this.postService.getPosts(params), HttpStatus.OK);
    }

    @PostMapping(path = "/forums/{forumId}/posts/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> addPost(Principal user, @PathVariable(value = "forumId") int forumId, @RequestBody Map<String, String> params) {
        Post post = new Post();
        post.setContent(params.get("content"));
        post.setTitle(params.get("title"));
        post.setForumId(this.forumService.getForumById(forumId));
        post.setUserId(this.userService.getUserByUn(user.getName()));
        post.setParentId(this.postService.getPostById(Integer.parseInt(params.get("parent_id"))));
        return new ResponseEntity<>(this.postService.addOrUpdatePost(post), HttpStatus.OK);
    }

//    @DeleteMapping("/posts/{postId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @CrossOrigin
//    public void deletePost(@PathVariable(value = "postId") int id) {
//        this.postService.deletePost(id);
//    }

    @DeleteMapping(path = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> deletePost(@PathVariable(value = "postId") int postId) {
        if (postService.getPostById(postId) != null) {
            postService.deletePost(postId);
            return new ResponseEntity<>("xoa thanh cong", HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
