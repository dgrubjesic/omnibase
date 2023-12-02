package com.omni.base.users.domains;


import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
public class UserEntity {

    @Id
    private final String id;
    private final String email;
    private final String password;
}
