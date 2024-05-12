package org.example.backend.service;

import org.example.backend.DTO.AddLikeRequest;
import org.springframework.http.ResponseEntity;

public interface LikeService {

    ResponseEntity<?> addLikeCount(AddLikeRequest addLikeRequest);

    ResponseEntity<?> getUserLikedPosts(AddLikeRequest addLikeRequest);

    ResponseEntity<?> getStatus(Integer userId, String postId);
}
