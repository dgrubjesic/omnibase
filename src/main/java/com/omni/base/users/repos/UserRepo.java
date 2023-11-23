package com.omni.base.users.repos;

import com.omni.base.users.domains.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends ReactiveCrudRepository<UserEntity, String> {
}
