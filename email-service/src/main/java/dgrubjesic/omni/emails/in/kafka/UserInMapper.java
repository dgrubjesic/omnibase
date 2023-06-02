package dgrubjesic.omni.emails.in.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.shared.user.UserServiceProto;
import org.mapstruct.Mapper;

import java.nio.ByteBuffer;

@Mapper(componentModel = "spring")
public interface UserInMapper {


    default UserServiceProto map(ByteBuffer byteBuffer) {
        try {
            return UserServiceProto.parseFrom(byteBuffer);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
