package com.dgrubjesic.omnibase.gateway.services.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDeletionRequest {
    private String id;
}
