package dgrubjesic.omni.users.services;

import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.shared.user.shim.Meta;
import dgrubjesic.omni.shared.user.shim.Status;
import dgrubjesic.omni.users.out.OutMapper;
import dgrubjesic.omni.users.out.events.UserCreatedPublisher;
import dgrubjesic.omni.users.out.repos.UserRepo;
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

    private final UserRepo repo;
    private final UserCreatedPublisher publisher;
    private final OutMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public Mono<UserServiceProto> create(User request) {
        request.setId(TSID.Factory.getTsid());
        request.setPassword(encoder.encode(request.getPassword()));
        Meta meta = Meta.newBuilder()
                .setId(UUID.randomUUID().toString())
                .build();

        repo.save(mapper.map(request))
                .doOnEach(s -> meta.toBuilder().setStatus(mapper.map(s.getType())).build())
                .subscribe();


         publisher.notifyUserCreation(mapper.map(request, meta))
                 .subscribe();

         return Mono.just(mapper.map(request, meta));
    }
}
