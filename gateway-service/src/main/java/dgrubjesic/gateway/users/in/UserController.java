package dgrubjesic.gateway.users.in;

import dgrubjesic.gateway.services.GatewayService;
import dgrubjesic.gateway.users.in.domain.UsersInMapper;
import dgrubjesic.gateway.users.in.domain.UserDto;
import dgrubjesic.gateway.users.in.domain.UserDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final GatewayService gatewayService;
    private final UsersInMapper mapper;


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
