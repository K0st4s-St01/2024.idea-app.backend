package com.kstoi.project_idea_tracker.services;

import com.kstoi.project_idea_tracker.mappers.CommentMapper;
import com.kstoi.project_idea_tracker.models.dtos.CommentDto;
import com.kstoi.project_idea_tracker.models.entities.Comment;
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
public class CommentService {
    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final IdeaRepository ideaRepository;
    private final CommentMapper mapper;

    public Map<String,Object> create(CommentDto dto){
        var entity = mapper.toEntity(
                dto,
                userRepository.findById(dto.getUser()).orElseThrow(),
                ideaRepository.findById(dto.getIdea()).orElseThrow()
        );
        repository.save(entity);
        return Map.of("result","successful");

    }
    public Map<String,Object> read(Long id){
        Comment entity = repository.findById(id).orElseThrow();
        var dto = mapper.toDto(
                entity,
                entity.getUser().getUsername(),
                entity.getIdea().getId()
        );
        return Map.of("result","successful","dtos",dto);
    }
    public Map<String,Object> read(Integer page,Integer size){
        var dtos = new ArrayList<CommentDto>();
        for(var entity : repository.findAll(PageRequest.of(page,size))){
            dtos.add(
                    mapper.toDto(
                            entity,
                            entity.getUser().getUsername(),
                            entity.getIdea().getId()
                    )
            );
        }
        return Map.of("result","successful",
                "dtos",dtos
                ,"pages",repository.count()/size);
    }
    public Map<String,Object> update(CommentDto dto) throws Exception {
        if(repository.existsById(dto.getId())) {
            var entity = mapper.toEntity(
                    dto,
                    userRepository.findById(dto.getUser()).orElseThrow(),
                    ideaRepository.findById(dto.getIdea()).orElseThrow()
            );
            repository.save(entity);
            return Map.of("result", "successful");
        }else{
            throw new Exception("no such value");
        }
    }
    public Map<String,Object> delete(Long id){
        repository.deleteById(id);
        return Map.of("result","successful");
    }


    public Map<String, Object> readByIdea(Long idea, Integer page, Integer size) {
        var dtos = new ArrayList<CommentDto>();
        for(var entity : repository.findByIdea_Id(idea,PageRequest.of(page,size))){
            dtos.add(
                    mapper.toDto(
                            entity,
                            entity.getUser().getUsername(),
                            entity.getIdea().getId()
                    )
            );
        }
        return Map.of("result","successful",
                "dtos",dtos
                ,"pages", repository.countByIdea_Id(idea) /size);

    }
}
