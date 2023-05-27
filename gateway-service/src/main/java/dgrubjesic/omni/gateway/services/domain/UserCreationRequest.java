package dgrubjesic.omni.gateway.services.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreationRequest {
    private String user;
    private String password;
    private String email;
}
