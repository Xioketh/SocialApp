package org.example.backend.models;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.backend.models.enums.LikeStatus;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "likes")
public class Like {
    private static final int MAX_LENGTH_2 = 200;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userCode", nullable = false, length = MAX_LENGTH_2)
    private String userCode;


    @Column(name = "postId", nullable = false, length = MAX_LENGTH_2)
    private String postId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LikeStatus status;

//    public Like(id id, LikeStatus status) {
//        this.id = id;
//        this.status = status;
//    }


    public Like(Long id, LikeStatus status) {
        this.id = id;
        this.status = status;
    }
}

