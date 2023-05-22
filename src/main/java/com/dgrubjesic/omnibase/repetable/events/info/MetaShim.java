package com.dgrubjesic.omnibase.repetable.events.info;


import lombok.Data;

import java.util.List;

@Data
public class MetaShim {
    private ResponseStatus status;
    private List<String> info;
}
