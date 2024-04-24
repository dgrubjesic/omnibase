package com.omni.base.services.subscriptions;

import com.omni.base.services.subscriptions.entities.SubscriptionEntity;
import omni.base.proto.subscriptions.commands.SubscriptionCommands;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface Mapper {

    SubscriptionEntity map(SubscriptionCommands.Update command, boolean newEntity);
}
