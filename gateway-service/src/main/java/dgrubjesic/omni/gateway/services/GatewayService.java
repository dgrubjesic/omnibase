package dgrubjesic.omni.gateway.services;

import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.gateway.users.out.GatewayUserPort;
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
