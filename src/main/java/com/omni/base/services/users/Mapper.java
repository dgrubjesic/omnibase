package com.omni.base.services.users;


import com.omni.base.services.users.entities.UserEntity;
import omni.base.proto.users.commands.UserCommands;
import omni.base.proto.users.events.UserEvents;
import omni.base.proto.users.queries.UserQueries;
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
    public abstract UserEntity mapEntity(String id, UserCommands.CreateCommand command, boolean newEntity);

    public abstract UserEvents.Created mapEvent(UserEntity entity);

    @Named("hashPassword")
    String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public abstract UserQueries.ReadResponse mapInfo(UserEntity entity);

}
