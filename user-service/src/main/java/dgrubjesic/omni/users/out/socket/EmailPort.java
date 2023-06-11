package dgrubjesic.omni.users.out.socket;

import reactor.core.publisher.Mono;

public interface EmailPort {
    Mono<Boolean> emailExist(String email);
}
