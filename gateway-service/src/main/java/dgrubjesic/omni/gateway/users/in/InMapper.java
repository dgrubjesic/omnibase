package dgrubjesic.omni.gateway.users.in;

import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.gateway.users.in.domain.UserDto;
import dgrubjesic.omni.gateway.users.in.domain.UserDtoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InMapper {

    UserCreationRequest map(UserDto userDto);

    UserDtoResponse map(UserCreationResponse response);

    UserDeletionRequest map(String id);

}
