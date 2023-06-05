package dgrubjesic.omni.gateway.users.in;

import dgrubjesic.omni.gateway.users.in.domain.UserDto;
import dgrubjesic.omni.gateway.users.in.domain.UserDtoResponse;
import dgrubjesic.omni.shared.user.UserServiceProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InMapper {

    @Mapping(target = "creationRequest.name", source = "name")
    @Mapping(target = "creationRequest.password", source = "password")
    @Mapping(target = "creationRequest.email", source = "email")
    UserServiceProto map(UserDto userDto);

    @Mapping(target = "name", source = "creationResponse.name")
    @Mapping(target = "email", source = "creationResponse.email")
    UserDtoResponse map(UserServiceProto response);

    @Mapping(target = "deletionRequest.id", source = "id")
    UserServiceProto map(String id);

}
