package dgrubjesic.omni.users.in;

import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
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
public class RsocketListener {

    @MessageMapping("userCreationRequest")
    public Mono<ByteBuffer> currentMarketData(@Payload ByteBuffer request) {
        return Mono.just(request)
                .doOnNext(s -> log.info("hello from other service"));
    }
}
