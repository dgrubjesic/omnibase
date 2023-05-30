package dgrubjesic.omni.gateway.users.out.adapter;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.gateway.UserCreationResponseProto;
import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.gateway.users.out.GatewayUserPort;
import dgrubjesic.omni.gateway.users.out.domain.UsersOutMapper;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

@Service
@RequiredArgsConstructor
@Slf4j
public class RSocketUserAdapter implements GatewayUserPort {

    private final RSocketRequester requester;
    private final UsersOutMapper mapper;

    @Override
    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
        return Mono.just(request)
                .map(mapper::map)
                .flatMap(s -> requester.route("userCreationRequest").data(s.toByteArray()).retrieveMono(ByteBuffer.class))
                .map(mapper::map)
                .map(mapper::map)
                .doOnError(s -> log.error(s.getMessage()));
    }

    @Override
    public Mono<Void> requestUserDeactivation(UserDeletionRequest request) {
        return requester.route("userDeletionRequest")
                .data(mapper.map(request).toByteArray())
                .send();
    }
}
