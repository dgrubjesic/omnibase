package dgrubjesic.gateway.users.in.domain;

import dgrubjesic.gateway.services.domain.UserCreationRequest;
import dgrubjesic.gateway.services.domain.UserCreationResponse;
import dgrubjesic.gateway.services.domain.UserDeletionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersInMapper {

    UserCreationRequest map(UserDto userDto);

    UserDtoResponse map(UserCreationResponse response);

    UserDeletionRequest map(String id);

}
