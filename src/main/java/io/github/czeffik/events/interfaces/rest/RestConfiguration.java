package io.github.czeffik.events.interfaces.rest;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.boot.starter.autoconfigure.OpenApiAutoConfiguration;
import springfox.documentation.oas.annotations.EnableOpenApi;

@Import({

})
@ImportAutoConfiguration({
    DispatcherServletAutoConfiguration.class,
    ServletWebServerFactoryAutoConfiguration.class,
    EmbeddedWebServerFactoryCustomizerAutoConfiguration.class,
    OpenApiAutoConfiguration.class
})
@Configuration
@EnableWebMvc
@EnableOpenApi
public class RestConfiguration {

}
