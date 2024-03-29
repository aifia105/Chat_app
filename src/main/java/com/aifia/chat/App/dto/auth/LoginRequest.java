package com.aifia.chat.App.dto.auth;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    private String email;

    private String password;
}
