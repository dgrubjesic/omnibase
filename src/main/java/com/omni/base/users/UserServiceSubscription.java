package com.omni.base.users;

import omni.base.proto.user.events.UserEvents;
import reactor.core.publisher.Flux;

public interface UserServiceSubscription {

    Flux<UserEvents.UserCreated> userCreatedEvents(UserEvents.UserCreated userCreated);
}
