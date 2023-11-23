package com.omni.base.users.subscriptions;

import com.omni.base.users.UserServiceSubscription;
import jakarta.annotation.PostConstruct;
import omni.base.proto.user.events.UserEvents;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UserServiceSubscriptionImpl implements UserServiceSubscription {

    Flux<UserEvents.UserCreated> createdFlux;

    @Override
    public Flux<UserEvents.UserCreated> userCreatedEvents(UserEvents.UserCreated userCreated) {
        return createdFlux;
    }

    @PostConstruct
    public void makeItHot() {
//        createdFlux = Flux.just();
        createdFlux = createdFlux.publish();
    }
}
