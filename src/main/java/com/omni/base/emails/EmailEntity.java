package com.omni.base.emails;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "emails")
@Getter
@Setter
public class EmailEntity implements Persistable<String> {

    @Id
    private String id;
    private String email;
    private String userId;
    private Status status;

    @Transient
    private boolean newEmail;
    @Override
    public boolean isNew() {
        return this.newEmail || id == null;
    }
}
