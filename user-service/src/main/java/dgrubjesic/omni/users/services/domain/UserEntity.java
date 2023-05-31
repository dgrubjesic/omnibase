package dgrubjesic.omni.users.services.domain;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {

    private TSID id;
    private String user;
    private String pass;
    private String email;
}
