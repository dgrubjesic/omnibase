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
import java.util.Arrays;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RsocketListener {

    private final EmailInMapper mapper;
    private final EmailService service;

    @MessageMapping("emailConfirmedRequest")
    public Mono<Void> emailConfirmation(@Payload ByteBuffer request) {
        return Mono
                .just(request)
                .map(mapper::map)
                .flatMap(service::confirm);
    }

    @MessageMapping("emailExistsRequest")
    public Mono<ByteBuffer> emailExists(@Payload ByteBuffer email) {
        return Mono
                .just(email)
                .map(s -> new String(email.array()))
                .flatMap(service::exists);
    }
}
