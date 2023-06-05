package dgrubjesic.omni.emails.service.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {
    private Long id;
    private Long userId;
    private Status status;
}
