package dgrubjesic.omni.gateway.users.out.domain;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.gateway.UserCreationRequestProto;
import dgrubjesic.omni.gateway.UserCreationResponseProto;
import dgrubjesic.omni.gateway.UserDeletionRequestProto;
import org.mapstruct.Mapper;

import java.nio.ByteBuffer;

@Mapper(componentModel = "spring")
public interface UsersOutMapper {

    UserCreationRequestProto map(UserCreationRequest request);

    UserCreationResponse map(UserCreationResponseProto responseProto);

    UserDeletionRequestProto map(UserDeletionRequest req);

    default UserCreationResponseProto map(ByteBuffer byteBuffer)
    {
        try {
            return UserCreationResponseProto.parseFrom(byteBuffer);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
