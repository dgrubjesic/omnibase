package com.omni.base.users.mappers;

import com.omni.base.users.domains.UserEntity;
import omni.base.proto.user.commands.UserProto;
import omni.base.proto.user.events.UserEvents;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
    UserEntity map(String id, UserProto.UserCreateCommand command);

    UserEvents.UserCreated mapEvent(UserEntity s);
    UserProto.UserDetailResponse map(UserEntity s);
}
