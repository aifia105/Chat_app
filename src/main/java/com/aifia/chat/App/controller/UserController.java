package com.aifia.chat.App.controller;

import com.aifia.chat.App.model.User;
import com.aifia.chat.App.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/save")
    public ResponseEntity<User> SaveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.SaveUser(user));
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(value = "/updateProfile/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable String id, @RequestBody User user){
        return ResponseEntity.ok(userService.updateProfile(id,user));
    }

    @GetMapping(value = "/connectedUsers")
    public ResponseEntity<List<User>> getConnectedUsers(){
        return ResponseEntity.ok(userService.getConnectedUsers());
    }

}
