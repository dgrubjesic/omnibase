package dgrubjesic.omni.emails.in.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteBufferDeserializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public ReceiverOptions<Integer, ByteBuffer> receiverOptions(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
        props.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, true);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "email_service");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "email_service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteBufferDeserializer.class);
        ReceiverOptions<Integer, ByteBuffer> options = ReceiverOptions.create(props);
        return options.subscription(Collections.singleton("userEvents"));
    }

    @Bean
    public KafkaReceiver<Integer, ByteBuffer> kafkaReceiver() {
        return KafkaReceiver.create(receiverOptions());
    }
}
