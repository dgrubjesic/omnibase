package dgrubjesic.omni.emailservice.in.kafka;

import dgrubjesic.omni.emailservice.in.UserListener;
import dgrubjesic.omni.emailservice.service.EmailService;
import dgrubjesic.omni.shared.user.UserServiceProto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.receiver.KafkaReceiver;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListener implements UserListener {

    private final KafkaReceiver<Integer, UserServiceProto> receiver;
    private final EmailService service;

    @PostConstruct
    public void receiveUserEvents() {
            receiver.receive()
                .next()
                .map(ConsumerRecord::value)
                .flatMap(service::generateWelcomeMail)
                .subscribe();
    }
}
