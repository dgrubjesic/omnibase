package dgrubjesic.omni.emails.service.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailEntity {
    private Long id;
    private Long userId;
    private Status status;
}
