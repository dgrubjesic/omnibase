package dgrubjesic.omni.emails.service;

import dgrubjesic.omni.emails.service.domain.Email;
import dgrubjesic.omni.shared.user.data.UserDataProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(target = "userId", source = "id")
    Email map(UserDataProto dataProto);
}
