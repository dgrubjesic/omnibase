package com.dgrubjesic.omnibase.gateway.services.domain;

import lombok.Data;

@Data
public class UserCreationRequest {
    private String user;
    private String password;
    private String email;
}
