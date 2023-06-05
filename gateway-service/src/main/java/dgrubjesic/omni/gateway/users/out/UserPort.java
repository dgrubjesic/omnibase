package dgrubjesic.omni.gateway.users.out;

import dgrubjesic.omni.shared.user.UserServiceProto;
import reactor.core.publisher.Mono;


public interface UserPort {
    Mono<UserServiceProto> requestUserCreation(UserServiceProto request);

    Mono<Void> requestUserDeactivation(UserServiceProto request);
}
