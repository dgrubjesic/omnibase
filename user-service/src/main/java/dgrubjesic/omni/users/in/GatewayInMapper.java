package dgrubjesic.omni.users.in;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.users.UserCreationRequestProto;
import dgrubjesic.omni.users.UserCreationResponseProto;
import dgrubjesic.omni.users.services.domain.UserEntity;
import org.mapstruct.Mapper;

import java.nio.ByteBuffer;

@Mapper(componentModel = "spring")
public interface GatewayInMapper {

    default UserCreationRequestProto map(ByteBuffer byteBuffer){
        try {
            return UserCreationRequestProto.parseFrom(byteBuffer);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    UserEntity map(UserCreationRequestProto request);

    UserCreationResponseProto map(UserEntity userEntity);
}
