package com.omni.base.gateways.users.users.dto;

import omni.base.proto.user.create.UserProto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserDtoMapper {

    UserProto.UserCreateCommand map(UserDto dto);
}
