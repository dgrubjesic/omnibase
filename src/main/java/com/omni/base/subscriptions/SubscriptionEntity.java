package com.omni.base.subscriptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "subscriptions")
@Getter
@Setter
public class SubscriptionEntity implements Persistable<String> {

    @Id
    private String id;
    private String userId;
    private Status status;

    @Transient
    private boolean newSub;
    @Override
    public boolean isNew() {
        return this.newSub || id == null;
    }
}
