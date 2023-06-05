package dgrubjesic.omni.gateway.users.in;

import dgrubjesic.omni.gateway.users.in.domain.UserDto;
import dgrubjesic.omni.gateway.users.in.domain.UserDtoResponse;
import dgrubjesic.omni.shared.user.UserServiceProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InMapper {

    @Mapping(target = "creation.name", source = "name")
    @Mapping(target = "creation.password", source = "password")
    @Mapping(target = "creation.email", source = "email")
    UserServiceProto map(UserDto userDto);

    @Mapping(target = "status", source = "meta.status")
    @Mapping(target = "info", source = "meta.info")
    UserDtoResponse map(UserServiceProto response);

    @Mapping(target = "deletion.id", source = "id")
    UserServiceProto map(String id);

}
