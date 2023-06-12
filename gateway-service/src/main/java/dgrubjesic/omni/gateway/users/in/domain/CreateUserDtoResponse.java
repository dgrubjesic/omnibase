package dgrubjesic.omni.gateway.users.in.domain;


import lombok.Data;

@Data
public class CreateUserDtoResponse {
    private Long id;
    private String status;
    private String info;
}
