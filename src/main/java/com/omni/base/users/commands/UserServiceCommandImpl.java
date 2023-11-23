package com.omni.base.users.commands;

import com.omni.base.users.UserServiceCommand;
import com.omni.base.users.UserServiceSubscription;
import com.omni.base.users.domains.UserEntity;
import com.omni.base.users.mappers.UserMapper;
import com.omni.base.users.repos.UserRepo;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import omni.base.proto.user.commands.UserProto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceCommandImpl implements UserServiceCommand {

    private final UserRepo repo;
    private final UserMapper mapper;
    private final UserServiceSubscription subscription;
    @Override
    public Mono<UserProto.UserView> createUser(UserProto.UserCreateCommand command) {
        TSID id = TSID.fast();
        UserEntity entity = mapper.map(id.toString(), command);
        Mono<UserEntity> userEntityMono = repo.save(entity).share();
        userEntityMono.subscribe(s -> subscription.userCreatedEvents(mapper.mapEvent(s)));
        return userEntityMono.map(mapper::map);
    }
}
