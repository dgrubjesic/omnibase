package dgrubjesic.omni.emails.service;

import dgrubjesic.omni.emails.out.OutMapper;
import dgrubjesic.omni.emails.out.repo.EmailRepo;
import dgrubjesic.omni.emails.out.repo.domain.EmailEntity;
import dgrubjesic.omni.emails.out.repo.domain.Status;
import dgrubjesic.omni.emails.service.domain.Email;
import dgrubjesic.omni.shared.email.EmailServiceProto;
import dgrubjesic.omni.shared.user.UserServiceProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepo repo;
    private final ServiceMapper serviceMapper;
    private final OutMapper outMapper;

    public Flux<Void> generateMail(UserServiceProto proto) {
        Email email = serviceMapper.map(proto.getDataProto());
        EmailEntity entity = outMapper.map(email, Status.UNCONFIRMED, UUID.randomUUID().toString());
        return repo.save(entity).flatMapMany(this::sendConfirmationMail);
    }

    private Flux<Void> sendConfirmationMail(EmailEntity email) {
        return Flux.empty();
    }

    public Flux<Void> deleteAllMails(UserServiceProto proto) {
        return repo.findAllByUserId(proto.getDeletion().getId())
                .doOnNext(s -> s.setStatus(Status.REMOVED))
                .flatMap(repo::save)
                .flatMap(this::sendConfirmationMail);
    }

    public Mono<Void> confirm(EmailServiceProto proto) {
        return repo.findByConfirmationId(proto.getDataProto().getConfirmationId())
                .doOnNext(s -> s.setStatus(Status.CONFIRMED))
                .flatMap(repo::save)
                .then();
    }

    public Mono<ByteBuffer> exists(String email) {
        return repo.existsByEmail(email).map(s -> {
            if (s) {
                return ByteBuffer.wrap("true".getBytes());
            }
            return ByteBuffer.wrap("false".getBytes());
        });
    }
}
