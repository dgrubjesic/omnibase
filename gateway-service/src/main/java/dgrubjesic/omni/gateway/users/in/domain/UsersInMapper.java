package dgrubjesic.omni.gateway.users.in.domain;

import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersInMapper {

    UserCreationRequest map(UserDto userDto);

    UserDtoResponse map(UserCreationResponse response);

    UserDeletionRequest map(String id);

}
