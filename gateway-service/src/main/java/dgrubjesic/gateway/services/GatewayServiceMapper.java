package dgrubjesic.gateway.services;

import dgrubjesic.gateway.services.domain.UserCreationRequest;
import dgrubjesic.gateway.services.domain.UserCreationResponse;
import dgrubjesic.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.gateway.users.in.domain.UserDto;
import dgrubjesic.gateway.users.in.domain.UserDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GatewayServiceMapper {

    UserCreationRequest map(UserDto userDto);

    UserDtoResponse map(UserCreationResponse response);

    UserDeletionRequest map(String id);

}
