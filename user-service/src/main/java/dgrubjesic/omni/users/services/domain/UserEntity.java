package dgrubjesic.omni.users.services.domain;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("user_db.users")
public class UserEntity {

    private Long id;
    private String name;
    private String pass;
    private String email;
}
