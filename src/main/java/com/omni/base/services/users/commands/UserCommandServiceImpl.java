package com.omni.base.services.users.commands;

import com.omni.base.services.topics.UserCreatedTopic;
import com.omni.base.services.commands.UserCommandService;
import com.omni.base.services.users.Mapper;
import com.omni.base.services.users.repos.Repo;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import omni.base.proto.users.commands.Commands;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final Repo repo;
    private final Mapper mapper;
    private final UserCreatedTopic subscription;
    @Override
    public Mono<Boolean> create(Commands.UserCreateCommand command) {
        return repo.save(mapper.mapEntity(String.valueOf(TSID.fast()), command, true))
                .log("user created")
                .doOnSuccess(s -> subscription.notify(mapper.mapEvent(s)))
                .map(s -> true)
                .onErrorResume(this::fallback);
    }

    private Mono<Boolean> fallback(Throwable e) {
        return Mono.just(false);
    }
}
