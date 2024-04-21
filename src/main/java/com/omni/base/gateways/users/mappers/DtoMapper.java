package com.omni.base.gateways.users.mappers;

import com.omni.base.gateways.users.dtos.DtoRequest;
import omni.base.proto.users.commands.Commands;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DtoMapper {

    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    Commands.UserCreateCommand mapRequest(DtoRequest dto);
}
