package dgrubjesic.omni.emails.service.domain;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailEntity {
    private TSID id;
    private TSID userId;
}
