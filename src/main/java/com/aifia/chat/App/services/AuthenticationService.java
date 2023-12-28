package com.aifia.chat.App.services;


import com.aifia.chat.App.config.JwtUtil;
import com.aifia.chat.App.dto.auth.AuthenticationResponse;
import com.aifia.chat.App.dto.auth.LoginRequest;
import com.aifia.chat.App.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userService.findByEmail(loginRequest.getEmail());
        if (user == null){
            throw new RuntimeException("No user found");
        }
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .picture(user.getPicture())
                .password(user.getPassword())
                .onlineStatus(true)
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse register(User request){
        var user = userService.SaveUser(request);
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .id(request.getId())
                .username(request.getUsername())
                .email(request.getEmail())
                .picture(user.getPicture())
                .password(request.getPassword())
                .onlineStatus(true)
                .token(jwtToken)
                .build();
    }

    public User disconnect(String userId){
       return userService.disconnect(userId);
    }
}
