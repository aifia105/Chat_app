package com.aifia.chat.App.services;

import com.aifia.chat.App.model.User;
import com.aifia.chat.App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User SaveUser(User user){
        if (userAlreadyExists(user.getEmail())){
            throw new RuntimeException("user already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setOnlineStatus(true);
        return this.userRepository.save(user);
    }

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(()-> new RuntimeException("No user with this email"+ email));
    }


    public User getUserById(String id){
        if (id == null){
            throw new RuntimeException("id user already exists");
        }
        return userRepository.findById(id).orElse(null);
    }

    public User disconnect(String id){
     var storedSUser = userRepository.findById(id).orElse(null);
     if (storedSUser != null){
         storedSUser.setOnlineStatus(false);
         userRepository.save(storedSUser);
     }
     return storedSUser;
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
    public List<User> getConnectedUsers(){
        return new ArrayList<>(userRepository.findAllByOnlineStatus(true));
    }

    private Boolean userAlreadyExists(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return  user.isPresent();
    }
}
