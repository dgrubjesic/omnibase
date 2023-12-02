package com.omni.base.users.subscriptions;

import com.omni.base.users.UserCreatedSubscription;
import omni.base.proto.user.events.UserEvents;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class UserCreatedSubscriptionImpl implements UserCreatedSubscription {
    private Sinks.Many<UserEvents.UserCreated> createdFlux = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public void notify(UserEvents.UserCreated userCreated) {
        createdFlux.tryEmitNext(userCreated);
    }

    @Override
    public Flux<UserEvents.UserCreated> listen() {
        return createdFlux.asFlux();
    }
}
