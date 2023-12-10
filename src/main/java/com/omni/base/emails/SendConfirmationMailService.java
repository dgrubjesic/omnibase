package com.omni.base.emails;

import com.omni.base.users.UserCreatedSubscription;
import com.omni.base.users.UserQueryService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omni.base.proto.user.events.UserEvents;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendConfirmationMailService {

    private final UserCreatedSubscription userCreatedSubscription;
    private final UserQueryService userQueryService;

    @PostConstruct
    private void listener() {
        userCreatedSubscription.listen().log().subscribe(this::send);
    }

    private void send(UserEvents.UserCreated userCreated) {
        userQueryService
                .findById(userCreated.getId())
                .log("confirmation email");
        //TODO send email
    }
}