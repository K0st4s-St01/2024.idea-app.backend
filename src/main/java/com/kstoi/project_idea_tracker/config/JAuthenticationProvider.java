package com.kstoi.project_idea_tracker.config;

import com.kstoi.project_idea_tracker.models.dtos.UserDto;
import com.kstoi.project_idea_tracker.services.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@AllArgsConstructor
public class JAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication){
        String username = authentication.getName();
        UserDto user = (UserDto) userService.read(username).get("dtos");
            if(BCrypt.checkpw(
                    authentication.getCredentials().toString(),
                    user.getPassword()
            )){
                return new UsernamePasswordAuthenticationToken(username,user.getPassword());
            }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
