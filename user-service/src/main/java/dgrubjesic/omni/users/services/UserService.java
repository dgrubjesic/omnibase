package dgrubjesic.omni.users.services;

import dgrubjesic.omni.users.out.events.UserCreatedPublisher;
import dgrubjesic.omni.users.out.repos.UsersRepo;
import dgrubjesic.omni.users.services.domain.UserEntity;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepo repo;
    private final UserCreatedPublisher publisher;

    public Mono<UserEntity> create(UserEntity user) {
        return Mono.just(user)
                .doOnNext(s -> s.setId(TSID.fast().toLong()))
                .flatMap(repo::save)
                .doOnNext(publisher::notifyUserCreated);
    }
}
