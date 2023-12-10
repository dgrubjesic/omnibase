package com.omni.base.users.subscriptions;

import com.omni.base.users.UserCreatedSubscription;
import lombok.extern.slf4j.Slf4j;
import omni.base.proto.user.events.UserEvents;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
@Slf4j
public class UserCreatedSubscriptionImpl implements UserCreatedSubscription {
    private Sinks.Many<UserEvents.UserCreated> createdSink = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public void notify(UserEvents.UserCreated userCreated) {
        log.info("notify user created: {}", userCreated);
        createdSink.tryEmitNext(userCreated);
    }

    @Override
    public Flux<UserEvents.UserCreated> listen() {
        return createdSink.asFlux();
    }
}
