package dgrubjesic.omni.users.in;

import dgrubjesic.omni.users.UserCreationRequestProto;
import dgrubjesic.omni.users.UserCreationResponseProto;
import dgrubjesic.omni.users.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

@Component
@RequiredArgsConstructor
@Slf4j
public class GatewayListener {

    private final GatewayInMapper mapper;
    private final UserService service;

    @MessageMapping("userCreationRequest")
    public Mono<ByteBuffer> currentMarketData(@Payload ByteBuffer request) {
        return Mono
                .just(request)
                .map(mapper::map)
                .map(mapper::map)
                .flatMap(service::create)
                .map(mapper::map)
                .map(s -> ByteBuffer.wrap(s.toByteArray()));
    }
}
