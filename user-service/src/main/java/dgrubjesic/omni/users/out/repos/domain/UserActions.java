package dgrubjesic.omni.users.out.repos.domain;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@Table("user_db.user_actions")
public class UserActions {

    Long id;
    Long userId;
    Action action;

    public enum Action {
        CREATED,
        DELETED
    }
}
