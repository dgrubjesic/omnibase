package dgrubjesic.omni.users.in;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.services.domain.UserEntity;
import org.mapstruct.Mapper;

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

    UserEntity map(UserServiceProto request);

    UserServiceProto map(UserEntity userEntity);
}
