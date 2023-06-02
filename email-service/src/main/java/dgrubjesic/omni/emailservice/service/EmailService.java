package dgrubjesic.omni.emailservice.service;

import dgrubjesic.omni.emailservice.in.UserListener;
import dgrubjesic.omni.shared.user.UserServiceProto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final UserListener userListener;

    public Mono<UserServiceProto> generateWelcomeMail(UserServiceProto userServiceProto) {
        return null;
    }
}
