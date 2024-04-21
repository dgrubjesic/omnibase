package com.omni.base.services.users;


import com.omni.base.services.users.entities.UserEntity;
import omni.base.proto.users.commands.Commands;
import omni.base.proto.users.events.Events;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public abstract class Mapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(source = "command.password", target = "password", qualifiedByName = "hashPassword")
    public abstract UserEntity mapEntity(String id, Commands.UserCreateCommand command, Boolean isNew);

    public abstract Events.UserCreatedEvent mapEvent(UserEntity entity);

    @Named("hashPassword")
    String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
