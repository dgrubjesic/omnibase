package com.omni.base.subscriptions;

import omni.base.proto.user.create.UserProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SubscriptionMapper {

    @Mapping(target = "status", constant = "FREE")
    @Mapping(target = "userId", source = "userCreated.details.id")
    SubscriptionEntity mapEntity(String id, UserProto.Response userCreated, Boolean isNew);
}
