package dgrubjesic.omni.users.out;

import dgrubjesic.omni.shared.user.Status;
import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.shared.user.data.UserDataProto;
import dgrubjesic.omni.shared.user.data.UserDeletionDataProto;
import dgrubjesic.omni.shared.user.shim.Meta;
import dgrubjesic.omni.users.out.repos.domain.UserActions;
import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import dgrubjesic.omni.users.services.domain.User;
import io.hypersistence.tsid.TSID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import reactor.core.publisher.SignalType;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface OutMapper {

    UserEntity map(User request);


    @ValueMapping(target = "CREATED", source = "ON_NEXT")
    @ValueMapping(target = "FAILED", source = "ON_ERROR")
    @ValueMapping(target = "UNKNOWN", source = MappingConstants.ANY_REMAINING )
    Status map(SignalType type);

    UserActions map(Long userId, String status, LocalDateTime dateTime);


//    its not mapping properly hence manual map
    default UserServiceProto map(String id, Long userId, String email, Meta meta, Status status) {
        return UserServiceProto.newBuilder()
                .setId(id)
                .setMeta(
                        Meta.newBuilder()
                                .setInfo(meta.getInfo())
                                .build()
                )
                .setStatus(status)
                .setUserData(
                        UserDataProto.newBuilder()
                                .setId(userId)
                                .setEmail(email)
                                .build()
                )
                .build();
    }

    default Long map(TSID value){
        return value.toLong();
    }

    @Mapping(target = "status", source = "status")
    UserEntity map(UserEntity entity, String status);


    default UserServiceProto map(String id, Long userId, Status status) {
        return UserServiceProto.newBuilder()
                .setId(id)
                .setStatus(status)
                .setDeletion(
                        UserDeletionDataProto.newBuilder()
                                .setId(userId)
                                .build()
                )
                .build();
    }
}
