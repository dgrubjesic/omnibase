package com.dgrubjesic.omnibase.gateway.services;

import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationResponse;
import com.dgrubjesic.omnibase.gateway.users.out.GatewayUserPort;
import com.dgrubjesic.omnibase.repetable.events.info.MetaShim;
import com.dgrubjesic.omnibase.repetable.events.info.ResponseStatus;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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
                .assertNext(s -> registry.counter(any()))
                .verifyComplete();

        verify(registry).counter(any());
        verify(port).requestUserCreation(any());


    }
}
