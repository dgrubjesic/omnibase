package dgrubjesic.omni.gateway.users.out.adapter;

import dgrubjesic.omni.gateway.users.out.OutMapper;
import dgrubjesic.omni.gateway.users.out.UserPort;
import dgrubjesic.omni.shared.user.UserServiceProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

@Service
@RequiredArgsConstructor
@Slf4j
public class RSocketUserAdapter implements UserPort {

    private final RSocketRequester userRequester;
    private final OutMapper mapper;

    @Override
    public Mono<UserServiceProto> requestUserCreation(UserServiceProto request) {
        return Mono.just(request)
                .flatMap(s -> userRequester.route("userCreationRequest").data(s.toByteArray()).retrieveMono(ByteBuffer.class))
                .checkpoint("sent to user via tcp")
                .map(mapper::map)
                .doOnError(s -> log.error(s.getMessage()));
    }

    @Override
    public Mono<Void> requestUserDeactivation(UserServiceProto request) {
        return userRequester.route("userDeletionRequest")
                .data(request.toByteArray())
                .send();
    }
}
