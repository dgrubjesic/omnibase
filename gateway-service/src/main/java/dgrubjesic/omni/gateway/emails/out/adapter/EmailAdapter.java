package dgrubjesic.omni.gateway.emails.out.adapter;

import dgrubjesic.omni.gateway.emails.out.EmailPort;
import dgrubjesic.omni.shared.email.EmailServiceProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailAdapter implements EmailPort {

    private final RSocketRequester emailRequester;
    @Override
    public Mono<Void> confirmEmail(EmailServiceProto proto) {
        return emailRequester.route("emailConfirmedRequest")
                .data(proto.toByteArray())
                .send();
    }
}
