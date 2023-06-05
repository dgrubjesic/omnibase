package dgrubjesic.omni.users.in;

import dgrubjesic.omni.users.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RsocketListener {

    private final InMapper mapper;
    private final UserService service;

    @MessageMapping("userCreationRequest")
    public Mono<ByteBuffer> userCreation(@Payload ByteBuffer request) {
        return Mono
                .just(request)
                .map(mapper::map)
                .map(mapper::map)
                .flatMap(service::create)
                .map(s -> ByteBuffer.wrap(s.toByteArray()));
    }
}
