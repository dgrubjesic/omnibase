package dgrubjesic.omni.emailservice.in;

import dgrubjesic.omni.shared.user.UserServiceProto;
import reactor.core.publisher.Mono;

public interface UserListener {

    Mono<UserServiceProto> receiveUserEvents();
}
