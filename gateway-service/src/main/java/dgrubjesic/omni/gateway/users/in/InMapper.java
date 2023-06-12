package dgrubjesic.omni.gateway.users.in;

import dgrubjesic.omni.gateway.users.in.domain.CreateUserDto;
import dgrubjesic.omni.gateway.users.in.domain.CreateUserDtoResponse;
import dgrubjesic.omni.shared.user.UserServiceProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InMapper {

    @Mapping(target = "creation.name", source = "name")
    @Mapping(target = "creation.password", source = "password")
    @Mapping(target = "creation.email", source = "email")
    UserServiceProto map(CreateUserDto createUserDto);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "info", source = "meta.info")
    @Mapping(target = "id", source = "dataProto.id")
    CreateUserDtoResponse map(UserServiceProto response);

    @Mapping(target = "deletion.id", source = "id")
    UserServiceProto map(String id);

}
