package com.kstoi.project_idea_tracker.mappers;

import com.kstoi.project_idea_tracker.models.dtos.IdeaDto;
import com.kstoi.project_idea_tracker.models.entities.Comment;
import com.kstoi.project_idea_tracker.models.entities.Idea;
import com.kstoi.project_idea_tracker.models.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdeaMapper {
    public Idea toEntity(IdeaDto dto, User user, List<Comment> comments){
        return Idea.builder()
                .user(user)
                .title(dto.getTitle())
                .comments(comments)
                .id(dto.getId()!=null?dto.getId():null)
                .text(dto.getText())
                .build();
    }
    public IdeaDto toDto(Idea entity, String user, List<Long> comments){
        return IdeaDto.builder()
                .user(user)
                .title(entity.getTitle())
                .comments(comments)
                .id(entity.getId()!=null?entity.getId():null)
                .text(entity.getText())
                .build();
    }
}
