package com.omni.base.gateways.users;

import com.omni.base.gateways.users.dtos.DtoRequest;
import com.omni.base.gateways.users.mappers.DtoMapper;
import com.omni.base.services.commands.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final DtoMapper mapper;
    private final UserCommandService userCommandService;

    @PostMapping
    Mono<Boolean> create(@RequestBody DtoRequest dtoRequest) {
        return Mono.just(dtoRequest)
                .map(mapper::mapRequest)
                .flatMap(userCommandService::create);
    }
}
