package dgrubjesic.omni.users.out.socket.adapter;

import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.out.socket.EmailPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailPortAdapter implements EmailPort {

    private final RSocketRequester emailRequester;
    private final OutMapper outMapper;

    @Override
    public Mono<Boolean> emailExist(String email) {
        return Mono
                .just(email)
                .flatMap(s -> emailRequester
                        .route("emailExistsRequest")
                        .data(ByteBuffer.wrap(s.getBytes()))
                        .retrieveMono(ByteBuffer.class))
                .map(outMapper::map);
    }
}
