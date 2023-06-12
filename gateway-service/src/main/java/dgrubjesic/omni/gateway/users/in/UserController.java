package dgrubjesic.omni.gateway.users.in;

import dgrubjesic.omni.gateway.services.GatewayService;
import dgrubjesic.omni.gateway.users.in.domain.CreateUserDto;
import dgrubjesic.omni.gateway.users.in.domain.CreateUserDtoResponse;
import dgrubjesic.omni.gateway.users.in.domain.LoginUserDto;
import dgrubjesic.omni.shared.user.UserStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final GatewayService gatewayService;
    private final InMapper mapper;

    @PostMapping
    public Mono<CreateUserDtoResponse> createUser(@Valid @RequestBody CreateUserDto createUserDto, ServerHttpResponse response) {
        return Mono.just(createUserDto)
                .map(mapper::map)
                .flatMap(gatewayService::requestUserCreation)
                .map(s -> {
                    if (s.getStatus().equals(UserStatus.FAILED)) {
                        response.setStatusCode(HttpStatus.BAD_REQUEST);
                        return s;
                    }
                    return s;
                })
                .map(mapper::map);
    }

    @DeleteMapping
    public Mono<Void> deleteUser(@RequestParam String id) {
        return Mono.just(id)
                .map(mapper::map)
                .flatMap(gatewayService::requestUserDeactivation);
    }
}
