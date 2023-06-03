package dgrubjesic.omni.emails.out;

import dgrubjesic.omni.emails.service.domain.EmailEntity;
import io.hypersistence.tsid.TSID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmailRepo extends ReactiveCrudRepository<Long, EmailEntity> {
}
