package com.kstoi.project_idea_tracker.models.dtos;

import com.kstoi.project_idea_tracker.models.entities.Comment;
import com.kstoi.project_idea_tracker.models.entities.Idea;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {
    private String username;
    private String password;
    private String email;

    private List<Long> ideas = new ArrayList<>();

    private List<Long> comments = new ArrayList<>();
}
