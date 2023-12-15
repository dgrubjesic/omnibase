package com.omni.base.users.mappers;

import com.omni.base.users.domains.UserEntity;
import omni.base.proto.user.create.UserProto;
import omni.base.proto.user.events.UserEvents;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
    UserEntity mapEntity(String id, UserProto.UserCreateCommand command, Boolean isNew);

    UserEvents.UserCreated mapEvent(UserEntity entity);

    @Mapping(target = "details", source = "entity")
    @Mapping(target = "meta.status", constant = "SUCCESS")
    UserProto.Response mapSuccess(UserEntity entity);

    @Mapping(target = "meta.message", source = "error")
    @Mapping(target = "meta.status", constant = "FAIL")
    UserProto.Response mapFail(String error);
}
