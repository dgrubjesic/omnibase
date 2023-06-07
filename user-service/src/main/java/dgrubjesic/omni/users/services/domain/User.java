package dgrubjesic.omni.users.services.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String name;
    private String password;
    private String email;
}
