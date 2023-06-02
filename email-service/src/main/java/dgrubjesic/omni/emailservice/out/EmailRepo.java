package dgrubjesic.omni.emailservice.out;

import dgrubjesic.omni.emailservice.service.domain.EmailEntity;
import io.hypersistence.tsid.TSID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmailRepo extends ReactiveCrudRepository<TSID, EmailEntity> {
}
