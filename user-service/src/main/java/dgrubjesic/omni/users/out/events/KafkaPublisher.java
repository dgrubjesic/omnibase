package dgrubjesic.omni.users.out.events;

import dgrubjesic.omni.shared.user.UserServiceProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.nio.ByteBuffer;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaPublisher implements UserCreatedPublisher {

    private final KafkaSender<Integer, ByteBuffer> kafkaSender;

    @Override
    public Mono<Void> notifyUserCreation(UserServiceProto proto) {
        return Mono.just(proto)
                .map(s -> ByteBuffer.wrap(s.toByteArray()))
                .map(s -> SenderRecord.create("userEvents", 0, 122L, 1, s, null))
                .flatMap(this::send);
    }

    @Override
    public Mono<Void> notifyUserDeletion(UserServiceProto proto) {
        return Mono.just(proto)
                .map(s -> ByteBuffer.wrap(s.toByteArray()))
                .map(s -> SenderRecord.create("userEvents", 0, 122L, 1, s, null))
                .flatMap(this::send);
    }


    Mono<Void> send(SenderRecord<Integer, ByteBuffer, Object> senderRecord) {
        kafkaSender.send(Mono.just(senderRecord))
                .doOnNext(s -> log.info("sent to: " + s.recordMetadata().topic()))
                .doOnError(s -> log.debug(s.getMessage()))
                .subscribe();
        return Mono.empty();
    }
}