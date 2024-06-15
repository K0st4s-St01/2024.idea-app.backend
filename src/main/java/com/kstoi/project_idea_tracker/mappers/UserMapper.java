package com.kstoi.project_idea_tracker.mappers;

import com.kstoi.project_idea_tracker.models.dtos.UserDto;
import com.kstoi.project_idea_tracker.models.entities.Comment;
import com.kstoi.project_idea_tracker.models.entities.Idea;
import com.kstoi.project_idea_tracker.models.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public User toEntity(UserDto dto, List<Idea> ideas,List<Comment> comments){
        return User.builder()
                .ideas(ideas)
                .email(dto.getEmail())
                .password(dto.getPassword())
                .comments(comments)
                .username(dto.getUsername())
                .build();
    }
    public UserDto toDto(User entity, List<Long> ideas,List<Long> comments){
        return UserDto.builder()
                .ideas(ideas)
                .email(entity.getEmail())
                .password(entity.getPassword())
                .comments(comments)
                .username(entity.getUsername())
                .build();
    }
}
