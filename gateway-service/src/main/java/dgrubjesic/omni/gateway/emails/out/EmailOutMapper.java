package dgrubjesic.omni.gateway.emails.out;

import dgrubjesic.omni.shared.email.EmailServiceProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailOutMapper {


    @Mapping(target = "dataProto.confirmationId", source = "confirmationId")
    EmailServiceProto map(String confirmationId);


}
