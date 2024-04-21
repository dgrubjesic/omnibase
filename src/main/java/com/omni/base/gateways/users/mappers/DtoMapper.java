package com.omni.base.gateways.users.mappers;

import com.omni.base.gateways.users.dtos.UserCreateDto;
import com.omni.base.gateways.users.dtos.UserInfoDto;
import omni.base.proto.users.commands.Commands;
import omni.base.proto.users.queries.Queries;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DtoMapper {

    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    Commands.UserCreateCommand mapRequest(UserCreateDto dto);

    Queries.UserReadQuery mapRequest(String id);

    UserInfoDto mapResponse(Queries.UserInfo userInfo);
}
