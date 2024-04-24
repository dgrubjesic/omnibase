package com.omni.base.gateways.users.mappers;

import com.omni.base.gateways.users.dtos.UserCreateDto;
import com.omni.base.gateways.users.dtos.UserInfoDto;

import omni.base.proto.users.commands.UserCommands;
import omni.base.proto.users.queries.UserQueries;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DtoMapper {

    @Mapping(target = "uniqueName", source = "uniqueName")
    @Mapping(target = "password", source = "password")
    UserCommands.CreateCommand mapRequest(UserCreateDto dto);

    UserQueries.ReadQuery mapRequest(String id);

    UserInfoDto mapResponse(UserQueries.ReadResponse userInfo);
}
