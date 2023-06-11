package dgrubjesic.omni.gateway.users.out.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

@Component
public class UserSocketConfig {

    @Bean
    public RSocketRequester userRequester() {
        return RSocketRequester
                .builder()
                .tcp("localhost", 7001);
    }
}