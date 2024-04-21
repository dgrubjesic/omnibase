package com.omni.base.services.users.repos;

import com.omni.base.services.users.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends ReactiveCrudRepository<UserEntity, String> {
}
