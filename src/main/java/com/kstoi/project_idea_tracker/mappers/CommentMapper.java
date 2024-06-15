package com.kstoi.project_idea_tracker.mappers;

import com.kstoi.project_idea_tracker.models.dtos.CommentDto;
import com.kstoi.project_idea_tracker.models.entities.Comment;
import com.kstoi.project_idea_tracker.models.entities.Idea;
import com.kstoi.project_idea_tracker.models.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toEntity(CommentDto dto, User user, Idea idea){
        return Comment.builder()
                .user(user)
                .idea(idea)
                .id(dto.getId()!=null?dto.getId():null)
                .text(dto.getText())
                .build();
    }
    public CommentDto toDto(Comment entity, String user, Long idea){
        return CommentDto.builder()
                .user(user)
                .idea(idea)
                .id(entity.getId()!=null?entity.getId():null)
                .text(entity.getText())
                .build();
    }
}
