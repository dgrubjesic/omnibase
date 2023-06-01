package dgrubjesic.omni.users.in;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.services.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.nio.ByteBuffer;

@Mapper(componentModel = "spring")
public interface GatewayInMapper {

    default UserServiceProto map(ByteBuffer byteBuffer){
        try {
            return UserServiceProto.parseFrom(byteBuffer);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    @Mapping(target = "name", source = "creationRequest.name")
    @Mapping(target = "password", source = "creationRequest.password")
    @Mapping(target = "email", source = "creationRequest.email")
    UserEntity map(UserServiceProto request);

    @Mapping(target = "creationRequest.name", source = "name")
    @Mapping(target = "creationRequest.email", source = "email")
    UserServiceProto map(UserEntity userEntity);
}
