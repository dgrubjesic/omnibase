package com.dgrubjesic.omnibase.gateway.services.domain;

import com.dgrubjesic.omnibase.repetable.events.info.MetaShim;
import lombok.Data;

@Data
public class UserCreationResponse {
    private MetaShim metaShim;
    private String user;
    private String email;
}
