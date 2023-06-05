package dgrubjesic.omni.emails.service;

import dgrubjesic.omni.emails.out.EmailRepo;
import dgrubjesic.omni.shared.user.UserServiceProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepo repo;

    public Mono<UserServiceProto> generateWelcomeMail(UserServiceProto userServiceProto) {
        log.info("user %s tried to subscribe".formatted(userServiceProto.getCreation().getName()));
        repo.save()
        return Mono.empty();
    }
}
