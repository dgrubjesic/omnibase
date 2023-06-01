package dgrubjesic.omni.gateway.users.out.domain;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.shared.user.UserServiceProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.nio.ByteBuffer;

@Mapper(componentModel = "spring")
public interface UsersOutMapper {

    @Mapping(target = "creationRequest.name", source = "request.name")
    @Mapping(target = "creationRequest.password", source = "request.password")
    @Mapping(target = "creationRequest.email", source = "request.email")
    UserServiceProto map(UserCreationRequest request);

    @Mapping(target = "name", source = "responseProto.creationResponse.name")
    @Mapping(target = "email", source = "responseProto.creationResponse.email")
    UserCreationResponse map(UserServiceProto responseProto);

    @Mapping(target = "deletionRequest.id", source = "request.id")
    UserServiceProto map(UserDeletionRequest request);

    default UserServiceProto map(ByteBuffer byteBuffer)
    {
        try {
            return UserServiceProto.parseFrom(byteBuffer);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
