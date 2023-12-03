package com.omni.base.gateways.users;

import com.omni.base.gateways.users.users.dto.UserDto;
import com.omni.base.gateways.users.users.dto.UserDtoMapper;
import com.omni.base.users.UserCreateService;
import lombok.RequiredArgsConstructor;
import omni.base.proto.user.create.UserProto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserCreationController {

    private final UserCreateService userCreateService;
    private final UserDtoMapper mapper;

    @PostMapping
    Mono<UserProto.UserDetailResponse> create(@RequestBody UserDto userDto) {
        return userCreateService.create(mapper.map(userDto));
    }
}