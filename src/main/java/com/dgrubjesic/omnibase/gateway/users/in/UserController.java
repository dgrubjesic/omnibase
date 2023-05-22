package com.dgrubjesic.omnibase.gateway.users.in;

import com.dgrubjesic.omnibase.gateway.services.GatewayService;
import com.dgrubjesic.omnibase.gateway.services.GatewayServiceMapper;
import com.dgrubjesic.omnibase.gateway.users.in.domain.UserDto;
import com.dgrubjesic.omnibase.gateway.users.in.domain.UserDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final GatewayService gatewayService;
    private final GatewayServiceMapper mapper;


    @PostMapping("/user")
    public Mono<UserDtoResponse> createUser(@RequestBody UserDto userDto) {
        return Mono.just(userDto)
                .map(mapper::map)
                .flatMap(gatewayService::requestUserCreation)
                .map(mapper::map);
    }

    @DeleteMapping("/user?id={id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return Mono.just(id)
                .map(mapper::map)
                .flatMap(gatewayService::requestUserDeactivation)
                .then();
    }
}
