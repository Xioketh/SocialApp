package org.example.backend.repository;

import org.example.backend.models.Like;
import org.example.backend.models.Post;
import org.example.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    Optional<Post> findTopByOrderByIdDesc();

    @Query(value = "SELECT p FROM Post p WHERE p.id = (SELECT MAX(p2.id) FROM Post p2)")
    Optional<Post> findMaxById();

    @Query(value = "SELECT * from posts where status =1", nativeQuery = true)
    List<Post> getAllActivePosts();

    @Modifying
    @Query("UPDATE Post p SET p.status = 0 WHERE p.id= ?1 ")
    int deletePost(String id);


}

