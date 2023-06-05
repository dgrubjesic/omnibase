package dgrubjesic.omni.emails.in.kafka;

import dgrubjesic.omni.emails.in.UserInMapper;
import dgrubjesic.omni.emails.service.EmailService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;
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
            receiver.receive()
                .next()
                .map(ConsumerRecord::value)
                .map(mapper::map)
                .map(mapper::map)
                .flatMap(service::generateWelcomeMail)
                .subscribe();
    }
}
