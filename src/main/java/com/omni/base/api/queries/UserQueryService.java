package com.omni.base.api.queries;

import omni.base.proto.users.queries.Queries;
import reactor.core.publisher.Mono;

public interface UserQueryService {
    Mono<Queries.UserInfo> read(Queries.UserReadQuery userReadQuery);
}
