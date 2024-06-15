package com.kstoi.project_idea_tracker.models.dtos;

import com.kstoi.project_idea_tracker.models.entities.Idea;
import com.kstoi.project_idea_tracker.models.entities.User;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentDto {
    private Long id;
    private String text;

    private String user;

    private Long idea;
}
