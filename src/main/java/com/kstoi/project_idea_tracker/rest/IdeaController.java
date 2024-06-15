package com.kstoi.project_idea_tracker.rest;

import com.kstoi.project_idea_tracker.models.dtos.IdeaDto;
import com.kstoi.project_idea_tracker.services.IdeaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/idea")
@Slf4j
public class IdeaController {
    private final IdeaService service;

    @PostMapping
    public Map<String,Object> create(@RequestBody IdeaDto dto){
        try {
            log.info("create {}", dto);
            return service.create(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Map<String,Object> read(@PathVariable("id") Long id){
        try {
            log.info("read {}", id);
            return service.read(id);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }

    @GetMapping("/{page}/{size}")
    public Map<String,Object> read(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        try {
            log.info("read page {} , {}", page,size);
            return service.read(page, size);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }

    @GetMapping("/title/{title}/{page}/{size}")
    public Map<String,Object> read(@PathVariable("title") String title,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        try {
            log.info("read by title page {} , {} , {}", page,size,title);
            return service.read(page, size,title);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }
    @PutMapping
    public Map<String,Object> update(@RequestBody IdeaDto dto){
        try {
            log.info("update {}", dto);
            return service.update(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public Map<String,Object> delete(@PathVariable("id") Long id){
        try {
            log.info("delete {}", id);
            return service.delete(id);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }
}
