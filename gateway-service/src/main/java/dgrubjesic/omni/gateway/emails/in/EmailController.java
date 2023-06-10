package dgrubjesic.omni.gateway.emails.in;

import dgrubjesic.omni.gateway.emails.out.OutMapper;
import dgrubjesic.omni.gateway.services.GatewayService;
import dgrubjesic.omni.gateway.users.in.domain.UserDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final GatewayService gatewayService;
    private final OutMapper mapper;

    @PostMapping
    public Mono<Void> confirmEmail(@RequestParam String confirmId) {
        return Mono
                .just(confirmId)
                .map(mapper::map)
                .flatMap(gatewayService::confirmEmail);
    }
}
