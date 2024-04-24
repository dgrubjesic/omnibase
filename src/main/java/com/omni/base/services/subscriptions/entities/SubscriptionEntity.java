package com.omni.base.services.subscriptions.entities;

import lombok.Getter;
import lombok.Setter;
import omni.base.proto.subscriptions.commands.SubscriptionCommands;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Table(name = "subscriptions")
@Getter
@Setter
public class SubscriptionEntity implements Persistable<String> {

    @Id
    private String id;

    private SubscriptionCommands.Status status;

    @LastModifiedDate
    private ZonedDateTime modified;

    @Transient
    private boolean newEntity;

    @Override
    public boolean isNew() {
        return this.newEntity || id == null;
    }

}
