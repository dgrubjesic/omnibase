package dgrubjesic.gateway.users.out.adapter;

import dgrubjesic.gateway.services.domain.UserCreationRequest;
import dgrubjesic.gateway.services.domain.UserCreationResponse;
import dgrubjesic.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.gateway.services.domain.UserDeletionResponse;
import dgrubjesic.gateway.users.out.GatewayUserPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RpcUserAdapter implements GatewayUserPort {
    @Override
    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
        return null;
    }

    @Override
    public Mono<UserDeletionResponse> requestUserDeactivation(UserDeletionRequest request) {
        return null;
    }
}