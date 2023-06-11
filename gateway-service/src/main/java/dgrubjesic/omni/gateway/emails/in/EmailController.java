package dgrubjesic.omni.gateway.emails.in;

import dgrubjesic.omni.gateway.emails.out.EmailOutMapper;
import dgrubjesic.omni.gateway.services.GatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final GatewayService gatewayService;
    private final EmailOutMapper mapper;

    @PostMapping
    public Mono<Void> confirmEmail(@RequestParam String confirmId) {
        return Mono
                .just(confirmId)
                .map(mapper::map)
                .flatMap(gatewayService::confirmEmail);
    }
}
