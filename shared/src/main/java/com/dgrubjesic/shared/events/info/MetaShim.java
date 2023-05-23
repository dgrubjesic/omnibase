package com.dgrubjesic.shared.events.info;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MetaShim {
    private ResponseStatus status;
    private List<String> info;
}
