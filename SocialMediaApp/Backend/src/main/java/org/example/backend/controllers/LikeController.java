package org.example.backend.controllers;


import org.example.backend.DTO.AddLikeRequest;
import org.example.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/socialApp/likes")
@CrossOrigin
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    //add like
    @PostMapping("/add-like")
    public final ResponseEntity<?> addLikeCount(@RequestBody AddLikeRequest addLikeRequest) {
        return likeService.addLikeCount(addLikeRequest);
    }

    @GetMapping("/status")
    public final ResponseEntity<?> getStatus(@RequestParam("userId") Integer userId, @RequestParam("postId") String postId) {
        ResponseEntity<?>  response = likeService.getStatus(userId, postId);
        return response;
    }

    @PostMapping("/getUserLikes")
    public final ResponseEntity<?> getUserLikedPosts(@RequestBody AddLikeRequest addLikeRequest) {
        return likeService.getUserLikedPosts(addLikeRequest);
    }
}

