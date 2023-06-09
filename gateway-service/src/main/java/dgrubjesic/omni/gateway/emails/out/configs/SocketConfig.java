package dgrubjesic.omni.gateway.emails.out.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class SocketConfig {

    @Bean
    public RSocketRequester userRequester() {
        return RSocketRequester
                .builder()
                .tcp("localhost", 7002);
    }
}
