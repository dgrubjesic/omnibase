package com.omni.base.services.subscriptions.listeners;

import com.omni.base.api.topics.UserCreatedTopic;
import com.omni.base.services.subscriptions.services.SubscriptionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreatedListener {

    private final UserCreatedTopic userCreatedTopic;
    private final SubscriptionService service;

    @PostConstruct
    public void listen() {
        userCreatedTopic.listen().subscribe(service::createSubscription);
    }
}
