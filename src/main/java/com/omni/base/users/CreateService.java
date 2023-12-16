package com.omni.base.users;


import omni.base.proto.user.create.UserProto;
import reactor.core.publisher.Mono;

public interface CreateService {

    Mono<UserProto.Response> create(UserProto.UserCreateCommand user);
}
