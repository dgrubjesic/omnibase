package dgrubjesic.omni.users.out.events;

import dgrubjesic.omni.users.services.domain.UserEntity;

public interface UserCreatedPublisher {

    void notifyUserCreated(UserEntity entity);
}
