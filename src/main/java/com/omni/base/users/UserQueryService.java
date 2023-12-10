package com.omni.base.users;

import omni.base.proto.user.create.UserProto;
import reactor.core.publisher.Mono;

public interface UserQueryService {

    Mono<UserProto.UserDetailResponse> findById(String id);
}