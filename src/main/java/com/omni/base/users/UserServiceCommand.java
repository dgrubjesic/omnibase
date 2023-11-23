package com.omni.base.users;

import omni.base.proto.user.commands.UserProto;
import reactor.core.publisher.Mono;

public interface UserServiceCommand {

    Mono<UserProto.UserView> createUser(UserProto.UserCreateCommand user);
}
