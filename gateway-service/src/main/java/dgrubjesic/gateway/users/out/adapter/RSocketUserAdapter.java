package dgrubjesic.gateway.users.out.adapter;

import dgrubjesic.gateway.services.domain.UserCreationRequest;
import dgrubjesic.gateway.services.domain.UserCreationResponse;
import dgrubjesic.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.gateway.services.domain.UserDeletionResponse;
import dgrubjesic.gateway.users.out.GatewayUserPort;
import io.rsocket.rpc.testing.protobuf.SimpleServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RSocketUserAdapter implements GatewayUserPort {

    private final SimpleServiceClient client;

    @Override
    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
        return client.requestReply();
    }

    @Override
    public Mono<UserDeletionResponse> requestUserDeactivation(UserDeletionRequest request) {
        return null;
    }
}
