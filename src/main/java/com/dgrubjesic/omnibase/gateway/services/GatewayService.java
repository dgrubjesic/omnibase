package com.dgrubjesic.omnibase.gateway.services;

import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationResponse;
import com.dgrubjesic.omnibase.gateway.services.domain.UserDeletionRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserDeletionResponse;
import com.dgrubjesic.omnibase.gateway.users.in.domain.UserDtoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class GatewayService {
    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
        return null;
    }

    public Mono<UserDeletionResponse> requestUserDeactivation(UserDeletionRequest request) {
        return null;
    }
}
