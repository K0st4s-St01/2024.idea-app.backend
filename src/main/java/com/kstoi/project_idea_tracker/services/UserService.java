package com.kstoi.project_idea_tracker.services;

import com.kstoi.project_idea_tracker.mappers.UserMapper;
import com.kstoi.project_idea_tracker.models.dtos.UserDto;
import com.kstoi.project_idea_tracker.models.entities.Comment;
import com.kstoi.project_idea_tracker.models.entities.Idea;
import com.kstoi.project_idea_tracker.models.entities.User;
import com.kstoi.project_idea_tracker.repos.CommentRepository;
import com.kstoi.project_idea_tracker.repos.IdeaRepository;
import com.kstoi.project_idea_tracker.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final IdeaRepository ideaRepository;
    private final CommentRepository commentRepository;
    private final UserMapper mapper;

    public Map<String,Object> create(UserDto dto) throws Exception {
        if(repository.existsById(dto.getUsername())){
            throw new Exception("value already exists");
        }
        var entity = mapper.toEntity(
                dto,
                ideaRepository.findAllById(dto.getIdeas()),
                commentRepository.findAllById(dto.getComments())
        );
        repository.save(entity);
        return Map.of("result","successful");
    }
    public Map<String,Object> read(String id){
        User entity = repository.findById(id).orElseThrow();
        var ids = new ArrayList<Long>();
        var ids2 = new ArrayList<Long>();
        for(Comment x : entity.getComments()){
            ids.add(x.getId());
        }
        for(Idea x : entity.getIdeas()){
            ids2.add(x.getId());
        }
        var dto = mapper.toDto(
                entity,
                ids2,
                ids
        );
        return Map.of("result","successful","dtos",dto);
    }
    public Map<String,Object> read(Integer page,Integer size){
        var dtos = new ArrayList<UserDto>();
        for(var entity : repository.findAll(PageRequest.of(page,size))){
            var ids = new ArrayList<Long>();
            var ids2 = new ArrayList<Long>();
            for(Comment x : entity.getComments()){
                ids.add(x.getId());
            }
            for(Idea x : entity.getIdeas()){
                ids2.add(x.getId());
            }
            dtos.add(
                    mapper.toDto(
                            entity,
                            ids2,
                            ids
                    )
            );
        }
        return Map.of("result","successful"
                ,"dtos",dtos
        ,"pages",repository.count()/size);
    }
    public Map<String,Object> update(UserDto dto) throws Exception {
        if(repository.existsById(dto.getUsername())) {
            var entity = mapper.toEntity(
                    dto,
                    ideaRepository.findAllById(dto.getIdeas()),
                    commentRepository.findAllById(dto.getComments())
            );
            repository.save(entity);
            return Map.of("result", "successful");
        }else{
            throw new Exception("no such value");
        }
    }
    public Map<String,Object> delete(String id){
        repository.deleteById(id);
        return Map.of("result","successful");
    }


}
