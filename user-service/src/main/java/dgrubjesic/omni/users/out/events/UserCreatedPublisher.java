package dgrubjesic.omni.users.out.events;

import dgrubjesic.omni.shared.user.UserServiceProto;
import reactor.core.publisher.Mono;

public interface UserCreatedPublisher {

    Mono<Void> notifyUserCreation(UserServiceProto proto);
    Mono<Void> notifyUserDeletion(UserServiceProto proto);


}
