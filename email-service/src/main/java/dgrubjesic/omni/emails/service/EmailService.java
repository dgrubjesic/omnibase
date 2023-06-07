package dgrubjesic.omni.emails.service;

import dgrubjesic.omni.emails.out.OutMapper;
import dgrubjesic.omni.emails.out.repo.EmailRepo;
import dgrubjesic.omni.emails.out.repo.domain.EmailEntity;
import dgrubjesic.omni.emails.out.repo.domain.Status;
import dgrubjesic.omni.emails.service.domain.Email;
import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.shared.user.data.UserCreationDataProto;
import dgrubjesic.omni.shared.user.data.UserDataProto;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepo repo;
    private final ServiceMapper serviceMapper;
    private final OutMapper outMapper;

    public Mono<Void> generateMail(UserServiceProto proto) {
        Email email = serviceMapper.map(proto.getUserData());
        EmailEntity entity = outMapper.map(TSID.Factory.getTsid().toLong(), email, Status.UNCONFIRMED, UUID.randomUUID().toString());

        return repo.save(entity).flatMap(s -> sendConfirmationMail(entity)).then();
    }

    private Mono<Void> sendConfirmationMail(EmailEntity email) {
        return Mono.empty();
    }

}
