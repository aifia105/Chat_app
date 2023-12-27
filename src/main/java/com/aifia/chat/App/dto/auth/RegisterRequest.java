package com.aifia.chat.App.dto.auth;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    private String username;
    private byte[] image;
    private String email;
    private String password;
}
