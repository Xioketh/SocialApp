package org.example.backend.service;

import org.example.backend.DTO.PostDto;
import org.example.backend.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

//    List<PostDto> getPosts();

    ResponseEntity<?> savePost(PostDto postDto);
    int updatePost(PostDto postDto);
    ResponseEntity<List<Post>> getPosts();
    int deletePost(String postId);

//    PostDto updatePostById(String postId, PostDto postDto);
//
//    PostDto deletePostById(String postId);
//
//
//    List<PostDto> searchPostByCategoryOrTitle(String search);
//
//    //    ResponseEntity<?> addLikeCount(AddLikeRequest addLikeRequest);
//    List<PostDto> getPostsByUserId(String userId);

}
