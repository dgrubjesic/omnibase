package dgrubjesic.omni.users.services;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.out.events.UserCreatedPublisher;
import dgrubjesic.omni.users.out.repos.UsersRepo;
import dgrubjesic.omni.users.services.domain.User;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepo repo;
    private final UserCreatedPublisher publisher;
    private final OutMapper mapper;

    public Mono<UserServiceProto> create(UserServiceProto request) {
         Mono<UserServiceProto> userMono = Mono.just(request)
                 .map(s -> s.getCreationRequest().toBuilder().setEmail(new BCryptPasswordEncoder(10,
                         new SecureRandom())).build())
                .share();
         userMono.map(UserServiceProto::getCreationRequest)
                 .map(mapper::map)
                 .flatMap(repo::save)
                 .subscribeOn(Schedulers.parallel());

         userMono.flatMap(publisher::notifyUserCreated)
                 .subscribeOn(Schedulers.parallel());
         return userMono;
    }
}
