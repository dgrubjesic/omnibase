package com.omni.base.api.topics;


import omni.base.proto.users.events.UserEvents;
import reactor.core.publisher.Flux;

public interface UserCreatedTopic {

    void notify(UserEvents.Created userCreated);
    Flux<UserEvents.Created> listen();
}
