package dgrubjesic.omni.users.services.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {
    private String user;
    private String password;
    private String email;
}
