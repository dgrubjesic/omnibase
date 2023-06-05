package dgrubjesic.omni.users.services;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.out.events.UserCreatedPublisher;
import dgrubjesic.omni.users.out.repos.UserRepo;
import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;
    private final UserCreatedPublisher publisher;
    private final OutMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;


    public Mono<UserServiceProto> create(UserServiceProto request) {
         Mono<UserServiceProto> userMono = Mono.just(request).share();

         userMono.map(UserServiceProto::getCreationRequest)
                 .map(mapper::map)
                 .flatMap(this::persist)
                 .subscribeOn(Schedulers.parallel());

         userMono.flatMap(publisher::notifyUserCreated)
                 .subscribeOn(Schedulers.parallel());
         return userMono;
    }

    public Mono<UserEntity> persist(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return repo.save(userEntity);
    }
}
