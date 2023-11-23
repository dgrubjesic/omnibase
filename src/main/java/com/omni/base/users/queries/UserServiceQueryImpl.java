package com.omni.base.users.queries;

import com.omni.base.users.UserServiceQuery;
import io.hypersistence.tsid.TSID;
import omni.base.proto.user.commands.UserProto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class UserServiceQueryImpl implements UserServiceQuery {
    @Override
    public Mono<UserProto.UserView> findUserById(TSID userId) {
        return null;
    }

    @Override
    public Flux<UserProto.UserView> findAllUsersByIds(List<TSID> userIds) {
        return null;
    }
}
