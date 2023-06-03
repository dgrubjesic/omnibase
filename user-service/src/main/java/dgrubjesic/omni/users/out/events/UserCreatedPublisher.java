package dgrubjesic.omni.users.out.events;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.services.domain.User;
import reactor.core.publisher.Mono;

public interface UserCreatedPublisher {

    Mono<Void> notifyUserCreated(UserServiceProto proto);
}
