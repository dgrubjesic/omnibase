package com.omni.base.services.topics;

import omni.base.protos.users.events.UserEvents;
import reactor.core.publisher.Flux;

public interface UserCreatedTopic {

    void notify(UserEvents.UserCreatedEvent userCreated);
    Flux<UserEvents.UserCreatedEvent> listen();
}
