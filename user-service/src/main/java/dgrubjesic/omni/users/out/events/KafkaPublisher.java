package dgrubjesic.omni.users.out.events;

import dgrubjesic.omni.users.out.UserOutMapper;
import dgrubjesic.omni.users.services.domain.UserEntity;
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
    private final UserOutMapper userOutMapper;

    @Override
    public void notifyUserCreated(UserEntity entity) {
        var x = Mono.just(entity)
                .map(userOutMapper::map)
                .map(s -> ByteBuffer.wrap(s.toByteArray()))
                .map(s -> SenderRecord.create("userCreated", 0, 122L, 1, s, null));
        kafkaSender.send(x).subscribe();
    }
}