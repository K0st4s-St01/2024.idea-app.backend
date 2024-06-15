package com.kstoi.project_idea_tracker.rest;

import com.kstoi.project_idea_tracker.config.JAuthenticationProvider;
import com.kstoi.project_idea_tracker.models.dtos.UserDto;
import com.kstoi.project_idea_tracker.models.entities.User;
import com.kstoi.project_idea_tracker.services.UserService;
import com.kstoi.project_idea_tracker.services.auth.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user")
@Slf4j
public class UserController {
    private final UserService service;
    private final JAuthenticationProvider authProvider;
    private final JwtService jwtService;
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody UserDto dto){
        var auth = authProvider.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
        if(auth == null){
            return Map.of("result","login failed","token","");
        }else{
            return Map.of("result","successful","token",jwtService.getToken(auth.getName()));
        }
    }

    @PostMapping
    public Map<String,Object> create(@RequestBody UserDto dto){
        try {
            log.info("create {}", dto);
            return service.create(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Map<String,Object> read(@PathVariable("id") String id){
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
    @PutMapping
    public Map<String,Object> update(@RequestBody UserDto dto){
        try {
            log.info("update {}", dto);
            return service.update(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public Map<String,Object> delete(@PathVariable("id") String id){
        try {
            log.info("delete {}", id);
            return service.delete(id);
        }catch (Exception e){
            log.error(e.getMessage());
            return Map.of("result",e.getMessage());
        }
    }
}
