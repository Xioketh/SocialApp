package org.example.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "posts")
public class Post {
    private static final int MAX_LENGTH_2 = 200;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // user ID
    @Column(name = "user_id", nullable = false, length = MAX_LENGTH_2)
    private String userId;

    @Column(name = "description", nullable = false, length = 5000)
    private String description;

    @Column(name = "username", nullable = false, length = MAX_LENGTH_2)
    private String username;

    @Column(name = "status", nullable = false, length = 2)
    private int status;

    @Column(name = "title", nullable = false, length = MAX_LENGTH_2)
    private String title;

    @ElementCollection
    @CollectionTable(name = "post_image_url", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image_url")
    private List<String> imageUrl;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Like> likes = new HashSet<>();
}

