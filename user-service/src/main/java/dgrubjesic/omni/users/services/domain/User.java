package dgrubjesic.omni.users.services.domain;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
public class User {

    private TSID id;
    private String name;
    private String password;
    private String email;
}
