package dgrubjesic.gateway.users.out.adapter;

import com.dgrubjesic.omni.proto.UserServiceProto;
import com.dgrubjesic.omni.proto.UserServiceRpcClient;
import dgrubjesic.gateway.services.domain.UserCreationRequest;
import dgrubjesic.gateway.services.domain.UserCreationResponse;
import dgrubjesic.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.gateway.users.out.GatewayUserPort;
import dgrubjesic.gateway.users.out.domain.UsersOutMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RSocketUserAdapter implements GatewayUserPort {

    private final UserServiceRpcClient client;
    private final UsersOutMapper mapper;

    @Override
    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
        return Mono.just(request)
                .map(mapper::map)
                .flatMap(client::createUserRequest)
                .map(mapper::map);
    }

    @Override
    public Mono<Void> requestUserDeactivation(UserDeletionRequest request) {
        return Mono.just(request)
                .map(mapper::map)
                .flatMap(client::deleteUerRequest)
                .then();
    }
}
