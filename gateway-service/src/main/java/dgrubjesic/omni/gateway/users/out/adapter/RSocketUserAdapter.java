package dgrubjesic.omni.gateway.users.out.adapter;

import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.gateway.users.out.GatewayUserPort;
import dgrubjesic.omni.gateway.users.out.domain.UsersOutMapper;
import dgrubjesic.omni.gateway.UserServiceRpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RSocketUserAdapter implements GatewayUserPort {

//    private final UserServiceRpcClient client;
    private final UsersOutMapper mapper;

    @Override
    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
//        return Mono.just(request)
//                .map(mapper::map)
//                .flatMap(client::createUserRequest)
//                .map(mapper::map);
        return null;
    }

    @Override
    public Mono<Void> requestUserDeactivation(UserDeletionRequest request) {
//        return Mono.just(request)
//                .map(mapper::map)
//                .flatMap(client::deleteUerRequest)
//                .then();
        return null;
    }
}
