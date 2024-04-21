package com.omni.base.services.users;

import omni.base.proto.users.commands.UserCreate;
import omni.base.protos.users.events.UserEvents;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface Mapper {
    UserEntity mapEntity(String id, UserCreate.UserCreateCommand command, Boolean isNew);

    UserEvents.UserCreatedEvent mapEvent(UserEntity entity);
}
