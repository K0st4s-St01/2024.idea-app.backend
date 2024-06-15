package com.kstoi.project_idea_tracker.services;

import com.kstoi.project_idea_tracker.mappers.IdeaMapper;
import com.kstoi.project_idea_tracker.models.dtos.IdeaDto;
import com.kstoi.project_idea_tracker.models.entities.Comment;
import com.kstoi.project_idea_tracker.models.entities.Idea;
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
public class IdeaService {
    private final IdeaRepository repository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final IdeaMapper mapper;

    public Map<String,Object> create(IdeaDto dto){
        var entity = mapper.toEntity(
                dto,
                userRepository.findById(dto.getUser()).orElseThrow(),
                commentRepository.findAllById(dto.getComments())
        );
        repository.save(entity);
        return Map.of("result","successful");
    }
    public Map<String,Object> read(Long id){
        Idea entity = repository.findById(id).orElseThrow();
        var ids = new ArrayList<Long>();
        for(Comment x : entity.getComments()){
            ids.add(x.getId());
        }
        var dto = mapper.toDto(
                entity,
                entity.getUser().getUsername(),
                ids
        );
        return Map.of("result","successful","dtos",dto);
    }

    public Map<String,Object> read(Integer page,Integer size){
        var dtos = new ArrayList<IdeaDto>();
        for(var entity : repository.findAll(PageRequest.of(page,size))){
            var ids = new ArrayList<Long>();
            for(Comment x : entity.getComments()){
                ids.add(x.getId());
            }
            dtos.add(
                    mapper.toDto(
                            entity,
                            entity.getUser().getUsername(),
                            ids
                    )
            );
        }

        return Map.of("result","successful"
                ,"dtos",dtos
                ,"pages",repository.count()/size
        );
    }


    public Map<String,Object> read(Integer page,Integer size,String title){
        var dtos = new ArrayList<IdeaDto>();
        for(var entity : repository.search(title,PageRequest.of(page,size))){
            var ids = new ArrayList<Long>();
            for(Comment x : entity.getComments()){
                ids.add(x.getId());
            }
            dtos.add(
                    mapper.toDto(
                            entity,
                            entity.getUser().getUsername(),
                            ids
                    )
            );
        }

        return Map.of("result","successful"
                ,"dtos",dtos
                ,"pages", repository.countByTitle(title) /size
        );
    }

    public Map<String,Object> update(IdeaDto dto) throws Exception {
        if(repository.existsById(dto.getId())) {
            var entity = mapper.toEntity(
                    dto,
                    userRepository.findById(dto.getUser()).orElseThrow(),
                    commentRepository.findAllById(dto.getComments())
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


}
