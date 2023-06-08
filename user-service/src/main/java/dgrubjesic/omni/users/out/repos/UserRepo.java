package dgrubjesic.omni.users.out.repos;

import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepo extends ReactiveCrudRepository<UserEntity, Long> {


    Mono<UserEntity> findById(long id);
}
