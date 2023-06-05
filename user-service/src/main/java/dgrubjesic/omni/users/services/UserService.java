package dgrubjesic.omni.users.services;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.shared.user.data.UserCreationRequestDataProto;
import dgrubjesic.omni.shared.user.shim.Meta;
import dgrubjesic.omni.shared.user.shim.Status;
import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.out.events.UserCreatedPublisher;
import dgrubjesic.omni.users.out.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;
    private final UserCreatedPublisher publisher;
    private final OutMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public Mono<UserServiceProto> create(UserServiceProto request) {
        UserServiceProto proto = request.toBuilder().setCreationRequest(UserCreationRequestDataProto.newBuilder()
                        .setPassword(encoder.encode(request.getCreationRequest().getPassword()))
                        .build())
                .build();

        repo.save(mapper.map(request.getCreationRequest()))
                .doOnEach(s -> meta(proto, mapper.map(s.getType())))
                .subscribeOn(Schedulers.parallel());

         publisher.notifyUserCreation(proto)
                 .subscribeOn(Schedulers.parallel());

         return Mono.just(proto);
    }

    public UserServiceProto meta(UserServiceProto proto, Status status) {
        return proto.toBuilder().setMeta(
                Meta.newBuilder()
                        .setId(UUID.randomUUID().toString())
                        .setStatus(status)
                        .build()
        ).build();
    }
}
