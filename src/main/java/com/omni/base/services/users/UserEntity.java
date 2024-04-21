package com.omni.base.services.users;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users")
@Getter
@Setter
public class UserEntity implements Persistable<String> {

    @Id
    private String id;
    private String email;
    private String password;

    @Transient
    private boolean newUser;
    @Override
    public boolean isNew() {
        return this.newUser || id == null;
    }
}
