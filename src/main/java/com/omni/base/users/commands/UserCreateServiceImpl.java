package com.omni.base.users.commands;

import com.omni.base.users.UserCreatedSubscription;
import com.omni.base.users.UserCreateService;
import com.omni.base.users.domains.UserEntity;
import com.omni.base.users.mappers.UserMapper;
import com.omni.base.users.repos.UserRepo;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import omni.base.proto.user.create.UserProto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl implements UserCreateService {

    private final UserRepo repo;
    private final UserMapper mapper;
    private final UserCreatedSubscription subscription;
    @Override
    public Mono<UserProto.Response> create(UserProto.UserCreateCommand command) {
        return repo.save(mapper.mapEntity(String.valueOf(TSID.fast()), command, true))
                .log("create user")
                .doOnSuccess(s -> subscription.notify(mapper.mapEvent(s)))
                .map(mapper::mapSuccess)
                .onErrorResume(this::fallback);
    }

    private Mono<UserProto.Response> fallback(Throwable e) {
        return Mono.just(mapper.mapFail(e.getMessage()));
    }
}
