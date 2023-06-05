package dgrubjesic.omni.emails.in;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.emails.service.domain.Email;
import dgrubjesic.omni.shared.user.UserServiceProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    @Mapping(target = "", source = "creation.")
    Email map(UserServiceProto request);
}
