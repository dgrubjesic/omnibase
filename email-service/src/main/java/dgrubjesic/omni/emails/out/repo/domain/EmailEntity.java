package dgrubjesic.omni.emails.out.repo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("email_db.users")
public class EmailEntity {
}
