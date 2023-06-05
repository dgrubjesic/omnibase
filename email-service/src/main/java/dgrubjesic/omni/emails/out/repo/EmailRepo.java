package dgrubjesic.omni.emails.out.repo;

import dgrubjesic.omni.emails.service.domain.Email;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmailRepo extends ReactiveCrudRepository<Email, Long> {
}
