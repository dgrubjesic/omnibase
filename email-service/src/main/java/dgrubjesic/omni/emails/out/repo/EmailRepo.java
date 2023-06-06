package dgrubjesic.omni.emails.out.repo;

import dgrubjesic.omni.emails.out.repo.domain.EmailEntity;
import dgrubjesic.omni.emails.service.domain.Email;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface EmailRepo extends ReactiveCrudRepository<EmailEntity, Long> {
}
