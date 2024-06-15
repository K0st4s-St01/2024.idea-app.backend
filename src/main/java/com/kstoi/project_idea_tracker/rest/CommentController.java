package com.kstoi.project_idea_tracker.rest;

import com.kstoi.project_idea_tracker.models.dtos.CommentDto;
import com.kstoi.project_idea_tracker.services.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public Map<String,Object> create(@RequestBody CommentDto dto){
        try {
            log.info("create {}", dto);
            return commentService.create(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Map<String,Object> read(@PathVariable("id") Long id){
        try {
            log.info("read {}", id);
            return commentService.read(id);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }

    @GetMapping("/{page}/{size}")
    public Map<String,Object> read(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        try {
            log.info("read page {} , {}", page,size);
            return commentService.read(page, size);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }
    @PutMapping
    public Map<String,Object> update(@RequestBody CommentDto dto){
        try {
            log.info("update {}", dto);
            return commentService.update(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public Map<String,Object> delete(@PathVariable("id") Long id){
        try {
            log.info("delete {}", id);
            return commentService.delete(id);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }
    @GetMapping("/idea/{id}/{page}/{size}")
    public Map<String,Object> readByIdea(@PathVariable("id") Long idea,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        try {
            log.info("read page {} , {}", page,size);
            return commentService.readByIdea(idea,page,size);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }
}
