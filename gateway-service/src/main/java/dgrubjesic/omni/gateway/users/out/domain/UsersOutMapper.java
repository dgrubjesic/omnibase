package dgrubjesic.omni.gateway.users.out.domain;

import dgrubjesic.omni.gateway.services.domain.UserCreationRequest;
import dgrubjesic.omni.gateway.services.domain.UserCreationResponse;
import dgrubjesic.omni.gateway.services.domain.UserDeletionRequest;
import dgrubjesic.omni.gateway.UserCreationRequestProto;
import dgrubjesic.omni.gateway.UserCreationResponseProto;
import dgrubjesic.omni.gateway.UserDeletionRequestProto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersOutMapper {

    UserCreationRequestProto map(UserCreationRequest request);

    UserCreationResponse map(UserCreationResponseProto responseProto);

    UserDeletionRequestProto map(UserDeletionRequest req);
}
