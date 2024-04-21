package com.omni.base.services.users.topics;

import com.omni.base.api.topics.UserCreatedTopic;
import lombok.extern.slf4j.Slf4j;

import omni.base.proto.users.events.Events;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
@Slf4j
public class UserCreatedTopicImpl implements UserCreatedTopic {
    private Sinks.Many<Events.UserCreatedEvent> createdSink = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public void notify(Events.UserCreatedEvent userCreated) {
        log.info("notify user created: {}", userCreated);
        createdSink.tryEmitNext(userCreated);
    }

    @Override
    public Flux<Events.UserCreatedEvent> listen() {
        return createdSink.asFlux();
    }
}
