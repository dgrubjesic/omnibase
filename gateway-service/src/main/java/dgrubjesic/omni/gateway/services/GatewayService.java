package dgrubjesic.omni.gateway.services;

import dgrubjesic.omni.gateway.emails.out.EmailPort;
import dgrubjesic.omni.gateway.users.out.UserPort;
import dgrubjesic.omni.shared.email.EmailServiceProto;
import dgrubjesic.omni.shared.user.UserServiceProto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GatewayService {
    private final UserPort userPort;
    private final EmailPort emailPort;

    public Mono<UserServiceProto> requestUserCreation(UserServiceProto request) {
        return Mono.just(request)
                .flatMap(userPort::requestUserCreation);
    }

    public Mono<Void> requestUserDeactivation(UserServiceProto request) {
        return Mono.just(request)
                .flatMap(userPort::requestUserDeactivation);
    }

    public Mono<Void> confirmEmail(EmailServiceProto proto) {
        return Mono.just(proto)
                .flatMap(emailPort::confirmEmail);
    }
}
