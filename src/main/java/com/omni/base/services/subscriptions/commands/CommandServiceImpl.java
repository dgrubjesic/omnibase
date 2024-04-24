package com.omni.base.services.subscriptions.commands;

import com.omni.base.api.commands.SubscriptionCommandService;
import com.omni.base.services.subscriptions.Mapper;
import com.omni.base.services.subscriptions.repos.Repo;
import lombok.RequiredArgsConstructor;
import omni.base.proto.subscriptions.commands.SubscriptionCommands;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CommandServiceImpl implements SubscriptionCommandService {

    private final Repo repo;
    private final Mapper mapper;

    @Override
    public Mono<Boolean> updateStatus(SubscriptionCommands.Update command) {
        return repo.findById(command.getId())
                .flatMap(s -> {
                    s.setStatus(command.getStatus());
                    return repo.save(s);
                })
                .thenReturn(true)
                .onErrorResume(this::fallback);
    }

    private Mono<Boolean> fallback(Throwable e) {
        return Mono.just(false).log(e.getMessage());
    }
}
