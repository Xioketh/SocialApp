package org.example.backend.repository;


import org.example.backend.models.Like;
import org.example.backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findTopByOrderByIdDesc();

    @Query(value = "SELECT * FROM likes where post_id = ?2 and user_code = ?1", nativeQuery = true)
    Optional<Like> findByLikeRequest(String userCode, String postId);

    @Query(value = "SELECT * FROM likes where user_code = ?1", nativeQuery = true)
    List<Like> findByLikePostsByUserCode(String userCode);



//    Optional<Like> findByLikeId(int i);
//    @Query("SELECT l.status FROM User as u JOIN Like  as l ON u.id = l.likeId.userCode JOIN Post as p ON p.id = l.likeId.postId WHERE l.likeId.userCode = ?1 and l.likeId.postId = ?2")
//    Optional<Like> getStatus(Integer userId, String postId);
}

