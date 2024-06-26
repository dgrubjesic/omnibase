package com.omni.base.services.users.entities;

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
    private String uniqueName;
    private String password;

    @Transient
    private boolean newEntity;

    @Override
    public boolean isNew() {
        return this.newEntity || id == null;
    }
}
