package com.dgrubjesic.omnibase.gateway.users.out;

import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationResponse;
import com.dgrubjesic.omnibase.gateway.services.domain.UserDeletionRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserDeletionResponse;
import reactor.core.publisher.Mono;

public interface GatewayUserPort {

    Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request);

    Mono<UserDeletionResponse> requestUserDeactivation(UserDeletionRequest request);
}
