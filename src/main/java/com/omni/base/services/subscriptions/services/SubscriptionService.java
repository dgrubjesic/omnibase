package com.omni.base.services.subscriptions.services;

import com.omni.base.services.subscriptions.Mapper;
import com.omni.base.services.subscriptions.repos.Repo;
import lombok.RequiredArgsConstructor;
import omni.base.proto.users.events.UserEvents;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final Repo repo;
    private final Mapper mapper;

    public void createSubscription(UserEvents.Created userCreatedEvent) {
        repo.save(mapper.map(userCreatedEvent, true));
    }
}
