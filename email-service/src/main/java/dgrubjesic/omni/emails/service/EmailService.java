package dgrubjesic.omni.emails.service;

import dgrubjesic.omni.emails.out.repo.EmailRepo;
import dgrubjesic.omni.emails.service.domain.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepo repo;

    public Mono<Email> generateWelcomeMail(Email email) {
        //TODO send confirmation email
        repo.save()
        return Mono.empty();
    }
}
