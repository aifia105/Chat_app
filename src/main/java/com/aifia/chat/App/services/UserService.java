package com.aifia.chat.App.services;

import com.aifia.chat.App.model.User;
import com.aifia.chat.App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User SaveUser(User user){
        if (userAlreadyExists(user.getEmail())){
            throw new RuntimeException("user already exists");
        }
        //setup password and code
        user.setOnlineStatus(true);
        return this.userRepository.save(user);
    }


    public User getUserById(String id){
        if (id == null){
            throw new RuntimeException("id user already exists");
        }
        return userRepository.findById(id).orElse(null);
    }

    public void updateUserStatus(String id, Boolean status){
     var storedSUser = userRepository.findById(id).orElse(null);
     if (storedSUser != null){
         storedSUser.setOnlineStatus(status);
         userRepository.save(storedSUser);
     }
    }

    public User updateProfile(String id, User user){
        var storedUser = userRepository.findById(id).orElse(null);
        if (storedUser != null){
            storedUser.setId(user.getId());
            storedUser.setUsername(user.getUsername());
            storedUser.setEmail(user.getEmail());
            storedUser.setPassword(user.getPassword());
            storedUser.setOnlineStatus(true);
            userRepository.save(storedUser);
        }
        return storedUser;
    }
    public List<User> getConnectedUser(){
        return new ArrayList<>(userRepository.findAllByOnlineStatus(true));
    }

    private Boolean userAlreadyExists(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return  user.isPresent();
    }
}
