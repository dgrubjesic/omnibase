package dgrubjesic.omni.users.out;

import dgrubjesic.omni.shared.Meta;
import dgrubjesic.omni.shared.user.UserDataProto;
import dgrubjesic.omni.shared.user.UserDeletionDataProto;
import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.shared.user.UserStatus;
import dgrubjesic.omni.users.out.repos.domain.UserActions;
import dgrubjesic.omni.users.out.repos.domain.UserEntity;
import dgrubjesic.omni.users.services.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface OutMapper {

    UserEntity map(User request);

    UserActions map(Long userId, String status, LocalDateTime dateTime);


//    its not mapping properly hence manual map
    default UserServiceProto map(String id, Long userId, String email, Meta meta, UserStatus status) {
        return UserServiceProto.newBuilder()
                .setId(id)
                .setMeta(
                        Meta.newBuilder()
                                .setInfo(meta.getInfo())
                                .build()
                )
                .setStatus(status)
                .setDataProto(
                        UserDataProto.newBuilder()
                                .setId(userId)
                                .setEmail(email)
                                .build()
                )
                .build();
    }

    @Mapping(target = "status", source = "status")
    UserEntity map(UserEntity entity, String status);


    default UserServiceProto map(String id, Long userId, UserStatus status) {
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

    default Boolean map(ByteBuffer byteBuffer) {
        String trueOrFalse = new String(byteBuffer.array());
        if (trueOrFalse.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
}
