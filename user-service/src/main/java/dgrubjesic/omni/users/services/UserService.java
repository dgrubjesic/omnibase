package dgrubjesic.omni.users.services;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.shared.user.shim.Meta;
import dgrubjesic.omni.shared.user.shim.Status;
import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.out.events.UserCreatedPublisher;
import dgrubjesic.omni.users.out.repos.UserActionsRepo;
import dgrubjesic.omni.users.out.repos.UserRepo;
import dgrubjesic.omni.users.out.repos.domain.UserActions;
import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import dgrubjesic.omni.users.services.domain.User;
import io.hypersistence.tsid.TSID;
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

    private final UserRepo userRepo;
    private final UserActionsRepo actionsRepo;
    private final UserCreatedPublisher publisher;
    private final OutMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public Mono<UserServiceProto> create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Meta meta = Meta.newBuilder()
                .setId(UUID.randomUUID().toString())
                .build();

        UserEntity userEntity = mapper.map(user);
        userEntity.setId(TSID.Factory.getTsid().toLong());
        userEntity.setIsNew(true);
        return userRepo.save(userEntity).then(
                actionsRepo.save(mapper.map(userEntity.getId(), UserActions.Action.CREATED)))
                .map(s -> mapper.map(s.getUserId(), user, meta, Status.CREATED))
                .doOnNext(publisher::notifyUserCreation);
    }

    public Mono<Void> delete(UserServiceProto proto) {
        return userRepo.deleteById(proto.getDeletion().getId())
                .then(Mono.defer(() -> actionsRepo.save(mapper.map(proto.getDeletion().getId(), UserActions.Action.DELETED))))
                .then(publisher.notifyUserDeletion(proto))
                .then();
    }
}
