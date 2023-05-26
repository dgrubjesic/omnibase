package dgrubjesic.gateway.users.out.domain;

import com.dgrubjesic.omni.proto.UserCreationRequestProto;
import com.dgrubjesic.omni.proto.UserCreationResponseProto;
import com.dgrubjesic.omni.proto.UserDeletionRequestProto;
import dgrubjesic.gateway.services.domain.UserCreationRequest;
import dgrubjesic.gateway.services.domain.UserCreationResponse;
import dgrubjesic.gateway.services.domain.UserDeletionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersOutMapper {

    UserCreationRequestProto map(UserCreationRequest request);

    UserCreationResponse map(UserCreationResponseProto responseProto);

    UserDeletionRequestProto map(UserDeletionRequest req);
}
