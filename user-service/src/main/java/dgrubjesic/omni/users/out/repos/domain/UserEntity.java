package dgrubjesic.omni.users.out.repos.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_db.users")
public class UserEntity implements Persistable<Long> {

    @Id
    @Setter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String status;

    @Transient
    private boolean isNew = false;

    public void setIsNew(boolean isNew) { this.isNew = isNew; }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
