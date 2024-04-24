package com.omni.base.api.queries;

import omni.base.proto.users.queries.UserQueries;
import reactor.core.publisher.Mono;

public interface UserQueryService {
    Mono<UserQueries.ReadResponse> read(UserQueries.ReadQuery userReadQuery);
}
