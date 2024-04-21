package com.omni.base.services.users.queries;

import com.omni.base.services.queries.UserQueryService;
import com.omni.base.services.users.Mapper;
import com.omni.base.services.users.repos.Repo;
import lombok.RequiredArgsConstructor;
import omni.base.proto.users.queries.Queries;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements UserQueryService {

    private final Repo repo;
    private final Mapper mapper;

    @Override
    public Mono<Queries.UserInfo> read(Queries.UserReadQuery userReadQuery) {
        return repo
                .findById(userReadQuery.getId())
                .map(mapper::mapInfo);
    }
}
