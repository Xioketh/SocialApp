package org.example.backend.service.impl;

import org.apache.coyote.Response;
import org.example.backend.DTO.PostDto;
import org.example.backend.DTO.response.ResponseMessage;
import org.example.backend.Util.IdGenerationUtil;
import org.example.backend.models.Post;
import org.example.backend.repository.PostRepository;
import org.example.backend.repository.UserRepository;
import org.example.backend.service.PostService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IdGenerationUtil idGenerationUtil;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    @Autowired
    private UserRepository userRepository;

    /** Save a post */
    @Override
    public ResponseEntity<?> savePost(PostDto postDto) {
        try {
            System.out.println(postDto.getTitle());
            System.out.println(postDto.getUserId());
            System.out.println(postDto.getImageUrl());

            boolean isUserPresent = userRepository.existsByuserCode(postDto.getUserId());
            if (isUserPresent){
//                int id = idGenerationUtil.postCodeGenerator();
//                System.out.println(id);
//                postDto.setId(String.valueOf(id));
                Post post = modelMapper.map(postDto, Post.class);
                post.setStatus(1);
                postRepository.save(post);
                postDto.setId(String.valueOf(post.getId()));
                return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "success",  postDto), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "There Is no User",  null), HttpStatus.BAD_REQUEST);
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error("Error saving post: {}", e.getMessage());
        }
        return null;
    }

    @Transactional
    public int updatePost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setStatus(1);
        postRepository.save(post);
        return 1;
    }

    public ResponseEntity<List<Post>> getPosts() {
        try {
            List<Post> postList = postRepository.getAllActivePosts();
            List<PostDto> postDtos =new ArrayList<>();
            for (Post post : postList) {
                postDtos.add(this.modelMapper.map(post, PostDto.class));
            }
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } catch (DataAccessException e) {
            logger.error("Error saving post: {}", e.getMessage());
        }
        return null;
    }

    @Transactional
    public int deletePost(String id) {
        try {
            int x = postRepository.deletePost(id);
            return 1;
        } catch (DataAccessException e) {
            logger.error("Error saving post: {}", e.getMessage());
        }
        return 0;
    }

}
