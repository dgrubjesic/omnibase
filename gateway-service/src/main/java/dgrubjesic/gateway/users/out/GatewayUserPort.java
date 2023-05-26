package dgrubjesic.gateway.users.out;

import dgrubjesic.gateway.services.domain.UserCreationRequest;
import dgrubjesic.gateway.services.domain.UserCreationResponse;
import dgrubjesic.gateway.services.domain.UserDeletionRequest;
import reactor.core.publisher.Mono;

public interface GatewayUserPort {

    Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request);

    Mono<Void> requestUserDeactivation(UserDeletionRequest request);
}
