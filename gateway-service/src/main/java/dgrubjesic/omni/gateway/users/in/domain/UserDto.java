package dgrubjesic.omni.gateway.users.in.domain;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String password;
    private String email;
}
