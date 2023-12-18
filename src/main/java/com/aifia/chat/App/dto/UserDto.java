package com.aifia.chat.App.dto;

import com.aifia.chat.App.model.User;
import lombok.Builder;

@Builder
public class UserDto {


    private String id;
    private String username;
    private String email;
    private String password;
    private Boolean onlineStatus;




    public static UserDto fromEntity(User user){
        if(user == null){
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .onlineStatus(user.getOnlineStatus())
                .build();

    }

    public static User toEntity(UserDto userDto){
        if (userDto == null){
            return null;
        }
        User user = new User();
        user.setId(userDto.id);
        user.setUsername(userDto.username);
        user.setEmail(userDto.id);
        user.setPassword(userDto.password);
        user.setOnlineStatus(userDto.onlineStatus);
        return user;
    }
}
