package dgrubjesic.omni.emails.in.rsocket;

import dgrubjesic.omni.emails.in.EmailInMapper;
import dgrubjesic.omni.emails.service.EmailService;
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

    private final EmailInMapper mapper;
    private final EmailService service;

    @MessageMapping("emailConfirmed")
    public Mono<Void> emailConfirmation(@Payload ByteBuffer request) {
        return Mono
                .just(request)
                .map(mapper::map)
                .flatMap(service::create);
    }
}
