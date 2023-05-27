package dgrubjesic.omni.gateway.users.in;

import dgrubjesic.omni.gateway.services.GatewayService;
import dgrubjesic.omni.gateway.users.in.domain.UsersInMapper;
import dgrubjesic.omni.gateway.users.in.domain.UserDto;
import dgrubjesic.omni.gateway.users.in.domain.UserDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final GatewayService gatewayService;
    private final UsersInMapper mapper;


    @PostMapping
    public Mono<UserDtoResponse> createUser(@RequestBody UserDto userDto) {
        return Mono.just(userDto)
                .map(mapper::map)
                .flatMap(gatewayService::requestUserCreation)
                .map(mapper::map);
    }

    @DeleteMapping("?id={id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return Mono.just(id)
                .map(mapper::map)
                .flatMap(gatewayService::requestUserDeactivation)
                .then();
    }
}