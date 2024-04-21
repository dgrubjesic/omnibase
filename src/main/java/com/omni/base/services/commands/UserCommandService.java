package com.omni.base.services.commands;


import omni.base.proto.users.commands.UserCreate;
import reactor.core.publisher.Mono;

public interface UserCommandService {

    Mono<Boolean> create(UserCreate.UserCreateCommand user);
}
