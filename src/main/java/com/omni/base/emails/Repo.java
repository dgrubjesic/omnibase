package com.omni.base.emails;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends ReactiveCrudRepository<ConfirmationsEntity, String> {
}
