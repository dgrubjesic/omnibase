package dgrubjesic.omni.users.services;

import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.out.events.UserCreatedPublisher;
import dgrubjesic.omni.users.out.repos.UsersRepo;
import dgrubjesic.omni.users.services.domain.User;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepo repo;
    private final UserCreatedPublisher publisher;
    private final OutMapper mapper;

    public Mono<User> create(User user) {
         Mono<User> userMono = Mono.just(user)
                .doOnNext(s -> s.setId(TSID.Factory.getTsid()))
                .share();
         userMono.map(mapper::map)
                 .flatMap(repo::save)
                 .subscribeOn(Schedulers.parallel());
         userMono.map(mapper::mapProto)
                 .flatMap(publisher::notifyUserCreated)
                 .subscribeOn(Schedulers.parallel());
         return userMono;
    }
}
