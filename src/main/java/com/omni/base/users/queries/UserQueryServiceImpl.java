package com.omni.base.users.queries;

import com.omni.base.users.UserQueryService;
import com.omni.base.users.mappers.UserMapper;
import com.omni.base.users.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import omni.base.proto.user.create.UserProto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepo repo;
    private final UserMapper mapper;
    @Override
    public Mono<UserProto.UserDetailResponse> findById(String id) {
        return repo.findById(id).map(mapper::map);
    }
}
