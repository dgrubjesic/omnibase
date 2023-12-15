package com.omni.base.emails;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends ReactiveCrudRepository<EmailEntity, String> {
}
