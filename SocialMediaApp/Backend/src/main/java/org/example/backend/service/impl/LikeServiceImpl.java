package org.example.backend.service.impl;


import org.example.backend.DTO.AddLikeRequest;
import org.example.backend.Util.IdGenerationUtil;
import org.example.backend.models.Like;
import org.example.backend.models.LikeId;
import org.example.backend.models.Post;
import org.example.backend.models.User;
import org.example.backend.models.enums.LikeStatus;
import org.example.backend.repository.LikeRepository;
import org.example.backend.repository.PostRepository;
import org.example.backend.repository.UserRepository;
import org.example.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private IdGenerationUtil idGenerationUtil;

    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository,
                           PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public ResponseEntity<?> addLikeCount(AddLikeRequest addLikeRequest) {

        Optional<Like> like = likeRepository.findByLikeRequest(addLikeRequest.getUserCode(),addLikeRequest.getId());
        Optional<Post> post = postRepository.findById(addLikeRequest.getId());

        Post editPost = post.get();
        if (like.isPresent()) {
            if (like.get().getStatus() == LikeStatus.LIKE) {

                editPost.setLikeCount(editPost.getLikeCount() - 1);
                postRepository.save(editPost);

                like.get().setStatus(LikeStatus.NOT_LIKE);
                likeRepository.save(like.get());

                return ResponseEntity.ok("Like Decremented.");
            } else {

                editPost.setLikeCount(editPost.getLikeCount() + 1);
                postRepository.save(editPost);

                like.get().setStatus(LikeStatus.LIKE);
                likeRepository.save(like.get());

                return ResponseEntity.ok("Like Incremented.");
            }
        }else{
            Like newLike = new Like();

            newLike.setId(idGenerationUtil.LikeCodeGenerator());
            newLike.setPostId(addLikeRequest.getId());
            newLike.setUserCode(addLikeRequest.getUserCode());
            newLike.setStatus(LikeStatus.LIKE);

            likeRepository.save(newLike);

            editPost.setLikeCount(editPost.getLikeCount() + 1);
            postRepository.save(editPost);

            return ResponseEntity.ok("Like Incremented.");
        }

    }

    @Override
    public ResponseEntity<?> getStatus(Integer userId, String postId) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<?> getUserLikedPosts(AddLikeRequest addLikeRequest) {
        List<Like> like = likeRepository.findByLikePostsByUserCode(addLikeRequest.getUserCode());
        return ResponseEntity.ok(like);
    }
}

