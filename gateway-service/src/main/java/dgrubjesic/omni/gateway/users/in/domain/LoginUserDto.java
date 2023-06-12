package dgrubjesic.omni.gateway.users.in.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {

    @NotBlank
    private String password;
    @NotBlank
    private String email;
}
