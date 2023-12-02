package com.omni.base.users.commands;

import com.omni.base.users.UserCreatedSubscription;
import com.omni.base.users.UserCreateCommand;
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
public class UserCreateCommandImpl implements UserCreateCommand {

    private final UserRepo repo;
    private final UserMapper mapper;
    private final UserCreatedSubscription subscription;
    @Override
    public Mono<UserProto.UserDetailResponse> create(UserProto.UserCreateCommand command) {
        TSID id = TSID.fast();
        UserEntity entity = mapper.map(id.toString(), command);
        return repo.save(entity)
                .doOnSuccess(s -> subscription.notify(mapper.mapEvent(s)))
                .map(mapper::map);
    }
}
