package com.omni.base.users;

import omni.base.proto.user.commands.UserProto;
import reactor.core.publisher.Mono;

public interface UserCreateCommand {

    Mono<UserProto.UserDetailResponse> create(UserProto.UserCreateCommand user);
}
