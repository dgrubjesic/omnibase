package com.dgrubjesic.omnibase.gateway.users.out.adapter;

import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationResponse;
import com.dgrubjesic.omnibase.gateway.services.domain.UserDeletionRequest;
import com.dgrubjesic.omnibase.gateway.users.out.GatewayUserPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RpcUserAdapter implements GatewayUserPort {
    @Override
    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
        return null;
    }

    @Override
    public Mono<Void> requestUserDeactivation(UserDeletionRequest request) {
        return null;
    }
}
