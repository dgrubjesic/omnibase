package dgrubjesic.gateway.services.configs;

import com.dgrubjesic.omni.proto.UserServiceRpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RsocketConfig {

    @Bean
    public UserServiceRpcClient client(){
        return new UserServiceRpcClient();
    }
}
