package dgrubjesic.omni.users.out.repos.domain;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Data
@Builder
@Table("user_db.user_actions")
public class UserActions {

    private Long id;
    private Long userId;
    private String status;
    private LocalDateTime dateTime;
}
