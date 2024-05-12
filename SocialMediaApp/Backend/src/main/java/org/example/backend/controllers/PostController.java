package org.example.backend.controllers;


import org.example.backend.DTO.PostDto;
import org.example.backend.models.Post;
import org.example.backend.service.PostService;
import org.example.backend.service.impl.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/socialApp/post")
public class PostController {
    private PostService postService;

    @Autowired
    private CloudinaryService cloudinaryService;


    @Autowired
    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAll")
    public final ResponseEntity<List<Post>> getPosts()
            throws ParseException {
        return postService.getPosts();
    }

    @PostMapping("/create")
    public final ResponseEntity<?> savePost(@RequestBody final PostDto postDto)
            throws ParseException {
        return postService.savePost(postDto);
    }

    @PostMapping("/delete/{postId}")
    public int deletePost(@PathVariable String postId)
            throws ParseException {
        return postService.deletePost(postId);
    }

    @PostMapping("/updatePost")
    public int updatePost(@RequestBody PostDto postDto) throws IOException {
        return postService.updatePost(postDto);
    }

    @PostMapping("/uploadImages")
    public ResponseEntity<?> upload(@RequestParam("files") List<MultipartFile> files) throws IOException {
        return cloudinaryService.upload(files);
    }


}

