package dgrubjesic.omni.emailservice.in.kafka.config;

import dgrubjesic.omni.shared.user.UserServiceProto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public ReceiverOptions<Integer, UserServiceProto> receiverOptions(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
        props.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, true);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "email_service");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "email_service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        ReceiverOptions<Integer, UserServiceProto> options = ReceiverOptions.create(props);
        return options.subscription(Collections.singleton("userCreated"));
    }

    @Bean
    public KafkaReceiver<Integer, UserServiceProto> kafkaReceiver() {
        return KafkaReceiver.create(receiverOptions());
    }
}
