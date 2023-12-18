package com.aifia.chat.App.dto.auth;

import lombok.Builder;

@Builder
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
}
