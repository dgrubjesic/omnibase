package dgrubjesic.omni.users.in;

import dgrubjesic.omni.users.services.UserService;
import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.service.RSocketExchange;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RsocketListener {

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
