package com.omni.base.users;


import omni.base.proto.user.create.UserProto;
import reactor.core.publisher.Mono;

public interface UserCreateService {

    Mono<UserProto.UserDetailResponse> create(UserProto.UserCreateCommand user);
}
