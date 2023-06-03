package dgrubjesic.omni.users.out.repos;

import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends ReactiveCrudRepository<UserEntity, Long> {
}
