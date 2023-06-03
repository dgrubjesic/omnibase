package dgrubjesic.omni.users.out;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import dgrubjesic.omni.users.services.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OutMapper {

    UserEntity map(User domain);

    @Mapping(target = "creationRequest.name", source = "name")
    @Mapping(target = "creationRequest.email", source = "email")
    UserServiceProto mapProto(User domain);
}
