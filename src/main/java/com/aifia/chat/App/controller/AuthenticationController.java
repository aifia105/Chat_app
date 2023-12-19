package com.aifia.chat.App.controller;


import com.aifia.chat.App.dto.auth.AuthenticationResponse;
import com.aifia.chat.App.dto.auth.LoginRequest;
import com.aifia.chat.App.model.User;
import com.aifia.chat.App.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user){
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @PostMapping(value = "/disconnect/{id}")
    public ResponseEntity<User> disconnect(@PathVariable String id){
        return ResponseEntity.ok(authenticationService.disconnect(id));
    }
}
