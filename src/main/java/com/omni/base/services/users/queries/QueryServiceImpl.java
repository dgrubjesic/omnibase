package com.omni.base.services.users.queries;

import com.omni.base.api.queries.UserQueryService;
import com.omni.base.services.users.Mapper;
import com.omni.base.services.users.repos.Repo;
import lombok.RequiredArgsConstructor;
import omni.base.proto.users.queries.UserQueries;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements UserQueryService {

    private final Repo repo;
    private final Mapper mapper;

    @Override
    public Mono<UserQueries.ReadResponse> read(UserQueries.ReadQuery readQuery) {
        return repo
                .findById(readQuery.getId())
                .map(mapper::mapInfo);
    }
}
