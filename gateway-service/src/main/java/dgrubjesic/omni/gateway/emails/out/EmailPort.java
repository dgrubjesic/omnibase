package dgrubjesic.omni.gateway.emails.out;

import dgrubjesic.omni.shared.email.EmailServiceProto;
import reactor.core.publisher.Mono;

public interface EmailPort {


    Mono<Void> confirmEmail(EmailServiceProto proto);
}
