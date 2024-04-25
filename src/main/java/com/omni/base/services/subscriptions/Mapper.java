package com.omni.base.services.subscriptions;

import com.omni.base.services.subscriptions.entities.SubscriptionEntity;
import omni.base.proto.users.events.UserEvents;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING, implementationName = "SubscriptionMapperImpl")
public interface Mapper {

    @Mapping(target = "status", constant = "NA")
    SubscriptionEntity map(UserEvents.Created userCreatedEvent, boolean newEntity);
}
