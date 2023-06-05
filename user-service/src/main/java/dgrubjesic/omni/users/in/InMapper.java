package dgrubjesic.omni.users.in;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.shared.user.UserServiceProto;
import dgrubjesic.omni.users.services.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.nio.ByteBuffer;

@Mapper(componentModel = "spring")
public interface InMapper {

    default UserServiceProto map(ByteBuffer byteBuffer){
        try {
            return UserServiceProto.parseFrom(byteBuffer);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    @Mapping(target = "name", source = "creation.name")
    @Mapping(target = "password", source = "creation.password")
    @Mapping(target = "email", source = "creation.email")
    User map(UserServiceProto request);

}
