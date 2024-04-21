package com.omni.base.services.users;


import omni.base.proto.users.commands.Commands;
import omni.base.proto.users.events.Events;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface Mapper {
    UserEntity mapEntity(String id, Commands.UserCreateCommand command, Boolean isNew);

    Events.UserCreatedEvent mapEvent(UserEntity entity);
}
