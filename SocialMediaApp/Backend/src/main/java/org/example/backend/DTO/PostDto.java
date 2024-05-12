package org.example.backend.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String id;
    private String userId;
    private String username;
    private String description;
    private String title;
    private List<String> imageUrl;
    private Integer likeCount;

}
