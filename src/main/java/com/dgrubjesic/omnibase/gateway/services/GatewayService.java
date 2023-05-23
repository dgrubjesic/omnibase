package com.dgrubjesic.omnibase.gateway.services;

import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationResponse;
import com.dgrubjesic.omnibase.gateway.services.domain.UserDeletionRequest;
import com.dgrubjesic.omnibase.gateway.users.out.GatewayUserPort;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GatewayService {
    private final GatewayUserPort port;
    private final MeterRegistry registry;

    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
        return Mono
                .just(request)
                .doOnNext(s -> registry.counter("Gateway", "user", "create"))
                .flatMap(port::requestUserCreation);
    }

    public Mono<Void> requestUserDeactivation(UserDeletionRequest request) {
        return Mono
                .just(request)
                .doOnNext(s -> registry.counter("Gateway", "user", "delete"))
                .flatMap(port::requestUserDeactivation);
    }
}
