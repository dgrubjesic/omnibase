package com.dgrubjesic.omnibase.gateway.users.in.domain;

import lombok.Data;

@Data
public class UserDto {
    private String user;
    private String password;
    private String email;
}
