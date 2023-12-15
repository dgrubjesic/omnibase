package com.omni.base.emails;

import omni.base.proto.user.create.UserProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EmailMapper {

    @Mapping(target = "status", constant = "UNVERIFIED")
    @Mapping(target = "userId", source = "id")
    EmailEntity mapEntity(String id, UserProto.Response response, Boolean isNew);
}
