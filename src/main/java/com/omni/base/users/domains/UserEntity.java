package com.omni.base.users.domains;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserEntity {

    private final String id;
    private final String email;
    private final String password;
}
