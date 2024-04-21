package com.omni.base.api.topics;


import omni.base.proto.users.events.Events;
import reactor.core.publisher.Flux;

public interface UserCreatedTopic {

    void notify(Events.UserCreatedEvent userCreated);
    Flux<Events.UserCreatedEvent> listen();
}
