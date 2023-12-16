package com.omni.base.emails;

import com.omni.base.users.UserCreatedSubscription;
import com.omni.base.users.UserQueryService;
import io.hypersistence.tsid.TSID;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omni.base.proto.user.events.UserEvents;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendConfirmationMailService {

    private final UserCreatedSubscription userCreatedSubscription;
    private final UserQueryService userQueryService;
    private final Repo repo;
    private final Mapper mapper;

    @PostConstruct
    private void listener() {
        userCreatedSubscription.listen().log().subscribe(this::send);
    }

    private Mono<Void> send(UserEvents.UserCreated userCreated) {
        return userQueryService
                .findById(userCreated.getId())
                .log("confirmation email")
                .map(s -> repo.save(mapper.mapEntity(String.valueOf(TSID.fast()), s, true)))
                .then();
    }
}
