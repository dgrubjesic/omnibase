package com.dgrubjesic.omnibase.gateway.services;

import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationResponse;
import com.dgrubjesic.omnibase.gateway.services.domain.UserDeletionRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GatewayService {
    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
        return null;
    }

    public Mono<Void> requestUserDeactivation(UserDeletionRequest request) {
        return null;
    }
}
