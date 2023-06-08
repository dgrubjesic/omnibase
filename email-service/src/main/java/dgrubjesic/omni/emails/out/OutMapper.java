package dgrubjesic.omni.emails.out;

import dgrubjesic.omni.emails.out.repo.domain.EmailEntity;
import dgrubjesic.omni.emails.out.repo.domain.Status;
import dgrubjesic.omni.emails.service.domain.Email;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutMapper {


    EmailEntity map(Email email, Status status, String confirmationId);
}
