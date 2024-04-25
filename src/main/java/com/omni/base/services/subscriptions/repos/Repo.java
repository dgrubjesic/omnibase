package com.omni.base.services.subscriptions.repos;

import com.omni.base.services.subscriptions.entities.SubscriptionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "SubscriptionRepo")
public interface Repo extends ReactiveCrudRepository<SubscriptionEntity, String> {
}
