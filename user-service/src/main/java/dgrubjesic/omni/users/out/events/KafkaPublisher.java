package dgrubjesic.omni.users.out.events;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.services.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.nio.ByteBuffer;

@Service
@RequiredArgsConstructor
public class KafkaPublisher implements UserCreatedPublisher {

    private final KafkaSender<Integer, ByteBuffer> kafkaSender;
    private final OutMapper outMapper;

    @Override
    public Mono<Void> notifyUserCreated(UserServiceProto proto) {
        var x = Mono.just(proto)
                .map(s -> ByteBuffer.wrap(s.toByteArray()))
                .map(s -> SenderRecord.create("userCreated", 0, 122L, 1, s, null));
        return kafkaSender.send(x).then();
    }
}