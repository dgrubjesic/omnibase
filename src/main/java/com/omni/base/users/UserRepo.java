package com.omni.base.users;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends ReactiveCrudRepository<UserEntity, String> {
}
