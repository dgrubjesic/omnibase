package dgrubjesic.omni.emails.in.kafka;

import dgrubjesic.omni.emails.in.UserInMapper;
import dgrubjesic.omni.emails.service.EmailService;
import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.shared.user.UserStatus;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;

import java.nio.ByteBuffer;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListener {

    private final KafkaReceiver<Integer, ByteBuffer> receiver;
    private final UserInMapper mapper;
    private final EmailService service;

    @PostConstruct
    public void receiveUserEvents() {
            receiveEvents().subscribe();
    }

    public Flux<Void> receiveEvents() {
        return receiver.receive()
                .map(ConsumerRecord::value)
                .map(mapper::map)
                .groupBy(UserServiceProto::getStatus)
                .flatMap(s -> {
                    if (s.key().equals(UserStatus.CREATED)) {
                        return s.flatMap(service::generateMail);
                    }
                    if (s.key().equals(UserStatus.DEACTIVATED)) {
                        return s.flatMap(service::deleteAllMails);
                    }
                    return Flux.error(new Error("cannot find case"));
                });
    }
}
