package dgrubjesic.omni.users.out;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.services.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserOutMapper {

    @Mapping(target = "creationRequest.name", source = "name")
    @Mapping(target = "creationRequest.email", source = "email")
    UserServiceProto map(UserEntity entity);
}
