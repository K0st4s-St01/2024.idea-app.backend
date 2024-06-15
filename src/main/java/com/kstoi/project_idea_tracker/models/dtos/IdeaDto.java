package com.kstoi.project_idea_tracker.models.dtos;

import com.kstoi.project_idea_tracker.models.entities.Comment;
import com.kstoi.project_idea_tracker.models.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class IdeaDto {
    @Id
    private Long id;
    private String title;
    private String text;

    private String user;

    private List<Long> comments = new ArrayList<>();

}
