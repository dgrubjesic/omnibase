package dgrubjesic.omni.users.out.repos;

import dgrubjesic.omni.users.out.repos.domain.UserActions;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionsRepo extends ReactiveCrudRepository<UserActions, Long> {
}
