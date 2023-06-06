package dgrubjesic.omni.users.out.events;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.services.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

import java.nio.ByteBuffer;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaPublisher implements UserCreatedPublisher {

    private final KafkaSender<Integer, ByteBuffer> kafkaSender;

    @Override
    public Mono<Void> notifyUserCreation(UserServiceProto proto) {
        var x = Mono.just(proto)
                .map(s -> ByteBuffer.wrap(s.toByteArray()))
                .map(s -> SenderRecord.create("userCreated", 0, 122L, 1, s, null));
        kafkaSender.send(x)
                .doOnNext(s -> log.info(s.recordMetadata().toString()))
                .doOnError(s -> log.debug(s.getMessage()))
                .subscribe();
        return Mono.empty();
    }
}