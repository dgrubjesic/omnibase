package dgrubjesic.omni.emails.in;

import dgrubjesic.omni.shared.user.UserServiceProto;
import reactor.core.publisher.Mono;

public interface UserListener {

    void receiveUserEvents();
}
