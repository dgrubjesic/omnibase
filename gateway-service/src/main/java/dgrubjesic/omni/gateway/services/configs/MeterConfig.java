package dgrubjesic.omni.gateway.services.configs;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MeterConfig {

    @Bean
    public MeterRegistry registry() {
        return new LoggingMeterRegistry();
    }
}
