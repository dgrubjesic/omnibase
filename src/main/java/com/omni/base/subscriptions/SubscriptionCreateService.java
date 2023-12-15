package com.omni.base.subscriptions;

import com.omni.base.users.UserCreatedSubscription;
import com.omni.base.users.UserQueryService;
import io.hypersistence.tsid.TSID;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import omni.base.proto.user.events.UserEvents;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SubscriptionCreateService {

    private final UserCreatedSubscription userCreatedSubscription;
    private final UserQueryService userQueryService;
    private final SubscriptionRepo repo;
    private final SubscriptionMapper mapper;

    @PostConstruct
    private void listener() {
        userCreatedSubscription.listen().log().subscribe(this::defaultSubscription);
    }

    private Mono<Void> defaultSubscription(UserEvents.UserCreated userCreated) {
        return userQueryService
                .findById(userCreated.getId())
                .log("new free subscription")
                .flatMap(s -> repo.save(mapper.mapEntity(String.valueOf(TSID.fast()), s, true)))
                .then();
    }
}
