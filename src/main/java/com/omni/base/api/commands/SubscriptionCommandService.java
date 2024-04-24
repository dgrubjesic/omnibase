package com.omni.base.api.commands;

import omni.base.proto.subscriptions.commands.SubscriptionCommands;
import reactor.core.publisher.Mono;

public interface SubscriptionCommandService {

    Mono<Boolean> updateStatus(SubscriptionCommands.Update command);
}
