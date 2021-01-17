package io.github.czeffik.events.interfaces.rest;

import io.github.czeffik.events.interfaces.rest.information.InformationRestControllerConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.EnableWebFlux;
import springfox.boot.starter.autoconfigure.OpenApiAutoConfiguration;
import springfox.documentation.oas.annotations.EnableOpenApi;

@Import({
    InformationRestControllerConfiguration.class
})
@ImportAutoConfiguration({
    EmbeddedWebServerFactoryCustomizerAutoConfiguration.class,
    OpenApiAutoConfiguration.class,

    ReactiveWebServerFactoryAutoConfiguration.class,
    ErrorWebFluxAutoConfiguration.class,
    WebFluxAutoConfiguration.class,
    HttpHandlerAutoConfiguration.class,
})
@Configuration
@EnableWebFlux
@EnableOpenApi
public class RestConfiguration {

}
