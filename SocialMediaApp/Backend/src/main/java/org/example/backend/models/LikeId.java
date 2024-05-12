package org.example.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeId implements Serializable {
    @Column(name = "user_code")
    private String userCode;

    @Column(name = "post_id")
    private String postId;

}

