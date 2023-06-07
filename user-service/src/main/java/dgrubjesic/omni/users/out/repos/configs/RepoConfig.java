package dgrubjesic.omni.users.out.repos.configs;

import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import io.hypersistence.tsid.TSID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import reactor.core.publisher.Mono;

@Configuration
public class RepoConfig {

    @Bean
    BeforeConvertCallback<UserEntity> beforeConvertCallback() {

        return (entity, table) -> {
            if (entity.getId() == null) {
                entity.setId(TSID.Factory.getTsid().toLong());
            }
            return Mono.just(entity);
        };
    }
}
