package dgrubjesic.omni.gateway.users.out.configs;

import dgrubjesic.omni.gateway.UserCreationRequestProto;
import dgrubjesic.omni.gateway.UserCreationResponseProto;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SocketConfig {

    @Bean
    public RSocketRequester requester() {
        return RSocketRequester.builder().tcp("localhost", 8081);
    }


    @Bean
    public KafkaSender<String, UserCreationRequestProto> reactiveKafkaProducerTemplate() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaProtobufSerializer.class);
        producerProps.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaProtobufSerializer.class);
        producerProps.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaProtobufSerializer.class);
        producerProps.put("schema.registry.url", "http://localhost:8081");

        SenderOptions<String, UserCreationRequestProto> senderOptions =
                SenderOptions.<String, UserCreationRequestProto>create(producerProps).maxInFlight(1024);
        return KafkaSender.create(senderOptions);
    }

    protected Map<String, Object> kafkaConsumerProperties() {
        Map<String, Object> kafkaPropertiesMap = new HashMap<>();
//        kafkaPropertiesMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        kafkaPropertiesMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        kafkaPropertiesMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        kafkaPropertiesMap.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
//        kafkaPropertiesMap.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, KafkaProtobufDeserializer.class);
        return kafkaPropertiesMap;
    }

    protected ReceiverOptions<String, UserCreationResponseProto> kafkaReceiverOptions() {
        ReceiverOptions<String, UserCreationResponseProto> options = ReceiverOptions.create(kafkaConsumerProperties());
        return options.pollTimeout(Duration.ofMillis(1200)).subscription(List.of("consumerTopicName"));
    }

    @Bean
    KafkaReceiver<String, UserCreationResponseProto> kafkaReceiver() {
        return KafkaReceiver.create(kafkaReceiverOptions());
    }
}
