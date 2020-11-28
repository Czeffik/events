package io.github.czeffik.events;

import io.github.czeffik.events.infrastructure.DomainConfiguration;
import io.github.czeffik.events.infrastructure.InfrastructureConfiguration;
import io.github.czeffik.events.interfaces.InterfacesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

@Import({
    DomainConfiguration.class,
    InterfacesConfiguration.class,
    InfrastructureConfiguration.class
})
@SpringBootConfiguration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
