package com.omni.base.api.commands;


import omni.base.proto.users.commands.UserCommands;
import reactor.core.publisher.Mono;

public interface UserCommandService {

    Mono<Boolean> create(UserCommands.CreateCommand command);

    Mono<Boolean> isUnique(String uniqueName);
}
