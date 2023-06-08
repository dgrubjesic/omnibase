package dgrubjesic.omni.emails.out.repo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("email_db.emails")
public class EmailEntity {

    @Id
    private Long id;
    private String email;
    private Long userId;
    private Status status;
    private String confirmationId;
}
