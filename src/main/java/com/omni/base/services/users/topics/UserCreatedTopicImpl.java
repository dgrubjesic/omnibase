package com.omni.base.services.users.topics;

import com.omni.base.api.topics.UserCreatedTopic;
import lombok.extern.slf4j.Slf4j;

import omni.base.proto.users.events.UserEvents;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
@Slf4j
public class UserCreatedTopicImpl implements UserCreatedTopic {
    private Sinks.Many<UserEvents.Created> createdSink = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public void notify(UserEvents.Created userCreated) {
        log.info("notify user created: {}", userCreated);
        createdSink.tryEmitNext(userCreated);
    }

    @Override
    public Flux<UserEvents.Created> listen() {
        return createdSink.asFlux();
    }
}
