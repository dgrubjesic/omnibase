package com.dgrubjesic.omnibase.gateway.services.domain;

import com.dgrubjesic.omnibase.repetable.events.info.MetaShim;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreationResponse {
    private MetaShim metaShim;
    private String user;
    private String email;
}
