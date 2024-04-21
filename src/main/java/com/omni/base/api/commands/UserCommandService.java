package com.omni.base.api.commands;


import omni.base.proto.users.commands.Commands;
import reactor.core.publisher.Mono;

public interface UserCommandService {

    Mono<Boolean> create(Commands.UserCreateCommand user);
}
