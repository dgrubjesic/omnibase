package dgrubjesic.omni.gateway.users.out;

import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import reactor.core.publisher.Mono;

public interface GatewayUserPort {

    Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request);

    Mono<Void> requestUserDeactivation(UserDeletionRequest request);
}
