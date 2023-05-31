package dgrubjesic.omni.users.out.events;

import dgrubjesic.omni.users.services.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
@RequiredArgsConstructor
public class KafkaPublisher implements UserCreatedPublisher {

    private final KafkaSender<Integer, UserEntity> sender;
    @Override
    public void notifyUserCreated(UserEntity entity) {
        var x = Mono.just(entity)
                .map(s -> SenderRecord.create("userCreated", 1, 122L, 1, s, null));
        sender.send(x).subscribe();
    }