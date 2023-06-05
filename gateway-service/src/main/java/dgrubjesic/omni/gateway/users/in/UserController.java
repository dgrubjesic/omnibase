package dgrubjesic.omni.gateway.users.in;

import dgrubjesic.omni.gateway.services.GatewayService;
import dgrubjesic.omni.gateway.users.in.domain.UserDto;
import dgrubjesic.omni.gateway.users.in.domain.UserDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final GatewayService gatewayService;
    private final InMapper mapper;


    @PostMapping
    public Mono<UserDtoResponse> createUser(@Valid @RequestBody UserDto userDto) {
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
