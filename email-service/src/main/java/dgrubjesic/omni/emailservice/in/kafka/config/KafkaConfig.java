package dgrubjesic.omni.emailservice.in.kafka.config;

import dgrubjesic.omni.shared.user.UserServiceProto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public ReceiverOptions<Integer, UserServiceProto> receiverOptions(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-consumer");
        props.put(ProducerConfig.GROUP_ID_CONFIG, "sample-group");
        props.put(ProducerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ProducerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ProducerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return ReceiverOptions.create(props);
    }

    @Bean
    public KafkaReceiver<Integer, UserServiceProto> kafkaReceiver() {
        return KafkaReceiver.create(receiverOptions());
    }
}
