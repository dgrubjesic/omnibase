package com.omni.base.emails;

import omni.base.proto.user.create.UserProto;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface Mapper {

    @Mapping(target = "status", constant = "UNVERIFIED")
    @Mapping(target = "userId", source = "response.details.id")
    ConfirmationsEntity mapEntity(String id, UserProto.Response response, Boolean isNew);
}
