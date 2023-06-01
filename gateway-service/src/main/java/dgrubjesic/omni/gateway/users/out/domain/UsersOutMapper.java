package dgrubjesic.omni.gateway.users.out.domain;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.shared.user.UserServiceProto;
import org.mapstruct.Mapper;

import java.nio.ByteBuffer;

@Mapper(componentModel = "spring")
public interface UsersOutMapper {

    UserServiceProto map(UserCreationRequest request);

    UserCreationResponse map(UserServiceProto responseProto);

    UserServiceProto map(UserDeletionRequest req);

    default UserServiceProto map(ByteBuffer byteBuffer)
    {
        try {
            return UserServiceProto.parseFrom(byteBuffer);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
