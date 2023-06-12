package dgrubjesic.omni.gateway.users.in.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDto {

    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
}
