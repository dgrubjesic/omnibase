package dgrubjesic.omni.gateway.services.domain;

import com.dgrubjesic.shared.events.info.MetaShim;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreationResponse {
    private MetaShim metaShim;
    private String user;
    private String email;
}
