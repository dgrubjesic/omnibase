package com.omni.base.services.users.commands;

import com.omni.base.api.topics.UserCreatedTopic;
import com.omni.base.api.commands.UserCommandService;
import com.omni.base.services.users.Mapper;
import com.omni.base.services.users.repos.Repo;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import omni.base.proto.users.commands.UserCommands;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("UserCommandService")
@RequiredArgsConstructor
public class CommandServiceImpl implements UserCommandService {

    private final Repo repo;
    private final Mapper mapper;
    private final UserCreatedTopic userCreatedTopic;
    @Override
    public Mono<Boolean> create(UserCommands.CreateCommand command) {
        return repo.save(mapper.mapEntity(String.valueOf(TSID.fast()), command, true))
                .log("user created")
                .doOnNext(s -> userCreatedTopic.notify(mapper.mapEvent(s)))
                .thenReturn(true)
                .onErrorResume(this::fallback);
    }

    @Override
    public Mono<Boolean> isUnique(String uniqueName) {
        return repo.existsByUniqueName(uniqueName);
    }

    @Override
    public Mono<Boolean> updatePassword(String oldPassword, String newPassword) {
        return repo.findByPassword(oldPassword)
                .flatMap(s -> {
                    s.setPassword(newPassword);
                    return repo.save(s);
                })
                .thenReturn(true)
                .onErrorResume(this::fallback);
    }

    private Mono<Boolean> fallback(Throwable e) {
        return Mono.just(false).log(e.getMessage());
    }
}
