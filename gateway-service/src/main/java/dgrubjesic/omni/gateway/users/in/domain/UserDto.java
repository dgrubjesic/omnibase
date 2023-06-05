package dgrubjesic.omni.gateway.users.in.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
}
