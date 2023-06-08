package dgrubjesic.omni.users.services;

import dgrubjesic.omni.shared.user.Status;
import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.shared.user.shim.Meta;
import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.out.events.UserCreatedPublisher;
import dgrubjesic.omni.users.out.repos.UserActionsRepo;
import dgrubjesic.omni.users.out.repos.UserRepo;
import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import dgrubjesic.omni.users.services.domain.User;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;
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
        Meta meta = Meta.newBuilder().build();
        String eventId = UUID.randomUUID().toString();
        UserEntity userEntity = mapper.map(user);
        userEntity.setId(TSID.Factory.getTsid().toLong());
        userEntity.setStatus(Status.CREATED.toString());
        userEntity.setIsNew(true);

        return userRepo.save(userEntity)
                .then(actionsRepo.save(mapper.map(userEntity.getId(), Status.CREATED.toString(), LocalDateTime.now())))
                .map(s -> mapper.map(eventId, s.getUserId(), user.getEmail(), meta, Status.CREATED))
                .flatMap(publisher::notifyUserCreation)
                .thenReturn(mapper.map(eventId, userEntity.getId(), user.getEmail(), meta, Status.CREATED));

    }

    public Mono<Void> delete(UserServiceProto proto) {
        Meta meta = Meta.newBuilder().build();
        String eventId = UUID.randomUUID().toString();
        return userRepo.findById(proto.getDeletion().getId())
                .flatMap(s -> userRepo.save(mapper.map(s, Status.DEACTIVATED.toString())))
                .flatMap(s -> actionsRepo.save(mapper.map(s.getId(), Status.DEACTIVATED.toString(), LocalDateTime.now())))
                .flatMap(s -> publisher.notifyUserDeletion(mapper.map(eventId, s.getUserId(), Status.DEACTIVATED)))
                .then();
    }
}
