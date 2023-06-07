package dgrubjesic.omni.users.services;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.shared.user.shim.Meta;
import dgrubjesic.omni.shared.user.shim.Status;
import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.out.events.UserCreatedPublisher;
import dgrubjesic.omni.users.out.repos.UserRepo;
import dgrubjesic.omni.users.services.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;
    private final UserCreatedPublisher publisher;
    private final OutMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public Mono<UserServiceProto> create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Meta meta = Meta.newBuilder()
                .setId(UUID.randomUUID().toString())
                .build();

        return repo.save(mapper.map(user))
                .map(s -> mapper.map(s, user, meta, Status.CREATED))
                .doOnNext(publisher::notifyUserCreation);
    }

    public Mono<Void> delete(UserServiceProto proto) {
        return repo.deleteById(proto.getDeletion().getId());

    }
}
