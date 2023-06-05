package dgrubjesic.omni.gateway.services;

import dgrubjesic.omni.gateway.users.out.UserPort;
import dgrubjesic.omni.shared.user.UserServiceProto;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.observability.micrometer.Micrometer;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GatewayService {
    private final UserPort port;
    private final MeterRegistry registry;

    public Mono<UserServiceProto> requestUserCreation(UserServiceProto request) {
        return Mono.just(request)
                .tag("user", "create")
                .tap(Micrometer.metrics(registry))
                .flatMap(port::requestUserCreation);
    }

    public Mono<Void> requestUserDeactivation(UserServiceProto request) {
        return Mono.just(request)
                .tag("user", "delete")
                .tap(Micrometer.metrics(registry))
                .flatMap(port::requestUserDeactivation);
    }
}
