package dgrubjesic.omni.gateway.services.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreationRequest {
    private String name;
    private String password;
    private String email;
}
