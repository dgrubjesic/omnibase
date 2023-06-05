package dgrubjesic.omni.users.out;

import dgrubjesic.omni.shared.user.data.UserCreationRequestDataProto;
import dgrubjesic.omni.shared.user.shim.Status;
import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import reactor.core.publisher.SignalType;

@Mapper(componentModel = "spring")
public interface OutMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    UserEntity map(UserCreationRequestDataProto request);


    @ValueMapping(target = "CREATED", source = "ON_NEXT")
    @ValueMapping(target = "FAILED", source = "ON_ERROR")
    @ValueMapping(target = "FAILED", source = MappingConstants.ANY_REMAINING )
    Status map(SignalType type);
}
