package dgrubjesic.omni.users.services;

import dgrubjesic.omni.users.services.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    public Mono<UserEntity> create(UserEntity user) {
        return null;
    }
}
