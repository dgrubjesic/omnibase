package com.omni.base.users;

import io.hypersistence.tsid.TSID;
import omni.base.proto.user.commands.UserProto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserServiceQuery {

    Mono<UserProto.UserView> findUserById(TSID userId);
    Flux<UserProto.UserView> findAllUsersByIds(List<TSID> userIds);
}
