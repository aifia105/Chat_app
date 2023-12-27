package com.aifia.chat.App.dto.auth;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {

    private String id;
    private String username;
    private byte[] picture;
    private String email;
    private String password;
    private boolean onlineStatus;
    private String token;
}
