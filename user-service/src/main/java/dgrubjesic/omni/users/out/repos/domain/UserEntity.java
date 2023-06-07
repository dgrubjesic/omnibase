package dgrubjesic.omni.users.out.repos.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("user_db.users")
public class UserEntity implements Persistable<Long> {

    @Id
    private Long id;
    private String name;
    private String password;
    private String email;

    @Transient
    private boolean isNew = false;

    public void setIsNew(boolean isNew) { this.isNew = isNew; }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
