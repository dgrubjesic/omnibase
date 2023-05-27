package dgrubjesic.gateway.services.configs;

import com.dgrubjesic.omni.proto.UserServiceRpcClient;
import io.rsocket.RSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

@Component
//@ComponentScan(basePackageClasses = UserServiceRpcClient.class)
public class RsocketConfig {

//    @Autowired
//    private RSocketRequester.Builder builder;
//
//    @Bean
//    public RSocket rSocket() {
//        return builder.tcp("",8080).rsocket();
//    }


//    @Bean
//    @Autowired
//    public UserServiceRpcClient client(RSocket rSocket){
//        return new UserServiceRpcClient(rSocket);
//    }
}
