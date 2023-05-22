package com.dgrubjesic.omnibase.gateway.services;

import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserCreationResponse;
import com.dgrubjesic.omnibase.gateway.services.domain.UserDeletionRequest;
import com.dgrubjesic.omnibase.gateway.services.domain.UserDeletionResponse;
import com.dgrubjesic.omnibase.gateway.users.in.domain.UserDto;
import com.dgrubjesic.omnibase.gateway.users.in.domain.UserDtoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GatewayServiceMapper {

    UserCreationRequest map(UserDto userDto);

    UserDtoResponse map(UserCreationResponse response);

    UserDeletionRequest map(String id);

    UserDtoResponse map(UserDeletionResponse response);
}
