package dgrubjesic.omni.users.out.repos.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("user_db.users")
public class UserEntity {

    @Id
    private Long id;
    private String name;
    private String password;
    private String email;
}
