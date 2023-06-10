package dgrubjesic.omni.emails.in;

import com.google.protobuf.InvalidProtocolBufferException;
import dgrubjesic.omni.shared.email.EmailServiceProto;
import org.mapstruct.Mapper;

import java.nio.ByteBuffer;

@Mapper(componentModel = "spring")
public interface EmailInMapper {

    default EmailServiceProto map(ByteBuffer byteBuffer){
        try {
            return EmailServiceProto.parseFrom(byteBuffer);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
