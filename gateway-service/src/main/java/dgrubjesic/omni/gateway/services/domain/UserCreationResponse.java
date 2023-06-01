package dgrubjesic.omni.gateway.services.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreationResponse {
    private MetaShim metaShim;
    private String name;
    private String email;
}
