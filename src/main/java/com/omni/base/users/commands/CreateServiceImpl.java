package com.omni.base.users.commands;

import com.omni.base.users.UserCreatedSubscription;
import com.omni.base.users.CreateService;
import com.omni.base.users.Mapper;
import com.omni.base.users.Repo;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import omni.base.proto.user.create.UserProto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateServiceImpl implements CreateService {

    private final Repo repo;
    private final Mapper mapper;
    private final UserCreatedSubscription subscription;
    @Override
    public Mono<UserProto.Response> create(UserProto.UserCreateCommand command) {
        return repo.save(mapper.mapEntity(String.valueOf(TSID.fast()), command, true))
                .log("user created")
                .doOnSuccess(s -> subscription.notify(mapper.mapEvent(s)))
                .map(mapper::mapSuccess)
                .onErrorResume(this::fallback);
    }

    private Mono<UserProto.Response> fallback(Throwable e) {
        return Mono.just(mapper.mapFail(e.getMessage()));
    }
}
