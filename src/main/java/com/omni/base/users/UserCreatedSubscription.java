package com.omni.base.users;

import omni.base.proto.user.events.UserEvents;
import reactor.core.publisher.Flux;

public interface UserCreatedSubscription {

    void notify(UserEvents.UserCreated userCreated);
    Flux<UserEvents.UserCreated> listen();
}
