package dgrubjesic.omni.gateway.users.out.adapter;

import dgrubjesic.omni.gateway.UserCreationRequestProto;
import dgrubjesic.omni.gateway.UserCreationResponseProto;
import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.gateway.users.out.GatewayUserPort;
import dgrubjesic.omni.gateway.users.out.domain.UsersOutMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaUserAdapter implements GatewayUserPort {
    private final UsersOutMapper mapper;
    private KafkaSender<String, UserCreationRequestProto> kafkaSender;
    private KafkaReceiver<String, UserCreationResponseProto> kafkaReceiver;

    @Override
    public Mono<UserCreationResponse> requestUserCreation(UserCreationRequest request) {
        return kafkaSender.send(
                        Flux.just(request)
                                .map(mapper::map)
                                .map(i ->
                                        SenderRecord.create(
                                                new ProducerRecord<>("users", "1", i),
                                                i.getUser())
                                )
                )
                .thenMany(kafkaReceiver.receive())
                .filter(s -> s.value().getUser().equals("asd"))
                .next()
                .map(s -> mapper.map(s.value()));
    }

    @Override
    public Mono<Void> requestUserDeactivation(UserDeletionRequest request) {
        return null;
    }
}
