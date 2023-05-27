package dgrubjesic.omni.gateway.services;

import com.dgrubjesic.shared.events.info.MetaShim;
import com.dgrubjesic.shared.events.info.ResponseStatus;
import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.gateway.users.out.GatewayUserPort;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={GatewayService.class})
public class GatewayServiceUnitTest {

    @MockBean
    private GatewayUserPort port;

    @MockBean
    private MeterRegistry registry;

    @Autowired
    private GatewayService service;


    @Test
    public void gatewayCreateUserRequestTest() {
        UserCreationRequest request = UserCreationRequest.builder()
                .user("user")
                .password("pass")
                .email("user@email.com")
                .build();
        UserCreationResponse response = UserCreationResponse.builder()
                .user("user")
                .email("user@email.com")
                .metaShim(MetaShim.builder().status(ResponseStatus.SUCCESS).build())
                .build();
        when(port.requestUserCreation(request)).thenReturn(Mono.just(response));

        StepVerifier.create(service.requestUserCreation(request))
                .expectNextCount(1)
                .verifyComplete();

        verify(registry).counter("Gateway", "user", "create");
        verify(port).requestUserCreation(any());
    }

    @Test
    public void gatewayDeleteUserRequestTest() {
        UserDeletionRequest request = UserDeletionRequest.builder()
                .id("id")
                .build();
        when(port.requestUserDeactivation(request)).thenReturn(Mono.empty());

        StepVerifier.create(service.requestUserDeactivation(request))
                .expectNextCount(0)
                .verifyComplete();

        verify(registry).counter("Gateway", "user", "delete");
        verify(port).requestUserDeactivation(any());
    }
}
