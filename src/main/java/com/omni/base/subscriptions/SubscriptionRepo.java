package com.omni.base.subscriptions;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SubscriptionRepo extends ReactiveCrudRepository<SubscriptionEntity, String> {
}
