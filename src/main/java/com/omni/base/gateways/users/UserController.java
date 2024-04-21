package com.omni.base.gateways.users;

import com.omni.base.gateways.users.dtos.UserCreateDto;
import com.omni.base.gateways.users.dtos.UserInfoDto;
import com.omni.base.gateways.users.mappers.DtoMapper;
import com.omni.base.api.commands.UserCommandService;
import com.omni.base.api.queries.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final DtoMapper mapper;
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping
    Mono<Boolean> create(@RequestBody UserCreateDto userCreateDto) {
        return Mono.just(userCreateDto)
                .map(mapper::mapRequest)
                .flatMap(userCommandService::create);
    }

    @GetMapping("/{userId}")
    Mono<UserInfoDto> read(@PathVariable String userId) {
        return Mono.just(userId)
                .map(mapper::mapRequest)
                .flatMap(userQueryService::read)
                .map(mapper::mapResponse);
    }
}
