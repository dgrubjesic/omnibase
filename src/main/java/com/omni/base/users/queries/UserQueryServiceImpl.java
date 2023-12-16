package com.omni.base.users.queries;

import com.omni.base.users.UserQueryService;
import com.omni.base.users.Mapper;
import com.omni.base.users.Repo;
import lombok.RequiredArgsConstructor;
import omni.base.proto.user.create.UserProto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final Repo repo;
    private final Mapper mapper;
    @Override
    public Mono<UserProto.Response> findById(String id) {
        return repo.findById(id).map(mapper::mapSuccess);
    }
}
