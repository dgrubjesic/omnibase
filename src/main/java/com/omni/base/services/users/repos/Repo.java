package com.omni.base.services.users.repos;

import com.omni.base.services.users.entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository(value = "UserRepo")
public interface Repo extends ReactiveCrudRepository<UserEntity, String> {

    Mono<Boolean> existsByUniqueName(String uniqueName);

    Mono<UserEntity> findByPassword(String password);
}
