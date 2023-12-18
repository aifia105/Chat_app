package com.aifia.chat.App.dto.auth;


import lombok.Builder;

@Builder
public class LoginRequest {

    private String email;

    private String password;
}
